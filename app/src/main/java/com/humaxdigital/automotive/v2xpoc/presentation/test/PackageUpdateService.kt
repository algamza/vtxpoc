package com.humaxdigital.automotive.v2xpoc.presentation.test

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.net.Uri.fromFile
import android.os.Build
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
import java.io.IOException


private const val ACTION_CHECK_AND_UPDATE_PACKAGE =
    "com.humaxdigital.automotive.v2xpoc.hidden.action.CHECK_AND_UPDATE_PACKAGE"

/**
 * A service class for handling asynchronous task request to check and update package
 * on a service on a separate handler thread.
 */
class PackageUpdateService : IntentService("PackageUpdateService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_CHECK_AND_UPDATE_PACKAGE -> {
                handleActionCheckAndUpdatePackage()
            }
        }
    }

    /**
     * Handle action for checking and updating new version of package
     */
    private fun handleActionCheckAndUpdatePackage() {
        var hiddenPath = getV2XHiddenPath()
        Log.i(TAG, "handleActionCheckAndUpdatePackage() " + hiddenPath)

        hiddenPath.let {
            var file = File(hiddenPath + File.separator + "V2X.apk")
            if (file.exists()) {
                // TODO: check version or hash
                if (INSTALL_BY_FILES_APP) {
                    val pm: PackageManager = getPackageManager()
                    startActivity(pm.getLaunchIntentForPackage("com.android.documentsui"))
                } else {
                    installPackage(file)
                }
            }
        }
    }

    private fun getV2XHiddenPath(): String? {
        var storagePath = getStoragePath(this)
        storagePath.let {
            val hiddenPath = storagePath + File.separator + ".v2xpoc"
            val hiddenFile = File(hiddenPath);
            if (hiddenFile.exists() && hiddenFile.isDirectory) {
                return hiddenPath;
            }
        }
        return null
    }

    private fun getStoragePath(context: Context?): String? {
        val mediaRwPath = "/mnt/media_rw"
        val mediaRwFile = File(mediaRwPath)
        var storagePath: String? = null

        if (mediaRwFile.exists() && mediaRwFile.isDirectory) {
            val files: Array<File> = mediaRwFile.listFiles()
            for (file in files) {
                if (file.isDirectory && !file.name.equals("sdcard1", ignoreCase = true)) {
                    val rootPath: String =
                        File.separator.toString() + "storage" + File.separator + file.name
                    Log.i(TAG, " " + file.name)
                    storagePath = mediaRwPath + File.separator + file.name
                    break
                }
            }
        }
        return storagePath
    }

    private fun installPackage(file: File) {
        val intent = Intent(Intent.ACTION_INSTALL_PACKAGE).apply {
            data = getApkUri(file)
            setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            )
            putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true)
            putExtra(Intent.EXTRA_RETURN_RESULT, true)
            putExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME, applicationInfo.packageName)
        }
        startActivity(intent)
    }

    private fun getApkUri(file: File): Uri? {
        // Before N, a MODE_WORLD_READABLE file could be passed via the ACTION_INSTALL_PACKAGE
        // Intent. Since N, MODE_WORLD_READABLE files are forbidden, and a FileProvider is
        // recommended.
        val useFileProvider: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

        // Copy the given asset out into a file so that it can be installed.
        // Returns the path to the file.
        val tempFilename = "tmp.apk"
        val buffer = ByteArray(16384)
        val fileMode =
            if (useFileProvider) Context.MODE_PRIVATE else Context.MODE_WORLD_READABLE
        try {
            FileInputStream(file).use { `is` ->
                openFileOutput(tempFilename, fileMode).use { fout ->
                    var n: Int
                    while (`is`.read(buffer).also { n = it } >= 0) {
                        fout.write(buffer, 0, n)
                    }
                }
            }
        } catch (e: IOException) {
            Log.i("InstallApk", "Failed to write temporary APK file", e)
        }
        return if (useFileProvider) {
            val toInstall = File(this.filesDir, tempFilename)
            FileProvider.getUriForFile(
                this, "com.humaxdigital.automotive.v2xpoc.fileprovider", toInstall
            )
        } else {
            fromFile(getFileStreamPath(tempFilename))
        }
    }

    companion object {
        const val DBG = false
        const val TAG = "PackageUpdateService"

        const val INSTALL_BY_FILES_APP = true

        /**
         * Starts this service to perform checking and update this package. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        @JvmStatic fun checkAndUpdatePackage(context: Context) {
            val intent = Intent(context, PackageUpdateService::class.java).apply {
                action = ACTION_CHECK_AND_UPDATE_PACKAGE
            }
            context.startService(intent)
        }
    }
}
