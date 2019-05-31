package com.example.packchannelinfo

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class PackchannelinfoPlugin(private val registrar: Registrar) : MethodCallHandler {

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "packchannelinfo")
            channel.setMethodCallHandler(PackchannelinfoPlugin(registrar))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "getchannelinfo") {
            result.success(getchannelinfo())
        } else {
            result.notImplemented()
        }
    }

    /**
     * 解压安装包zip,查找 META-INF/acost 开头的文件
     */
    private fun getchannelinfo(): String {
        val context = registrar.activeContext().applicationContext
        val appinfo = context.getApplicationInfo()
        val sourceDir = appinfo.sourceDir
        var zipfile: ZipFile? = null
        val prefix = "META-INF/acosch_"
        try {
            zipfile = ZipFile(sourceDir)
            val entries = zipfile!!.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement() as ZipEntry
                val entryName: String = entry.getName().toString()
                if (entryName.startsWith(prefix)) {
                    val channelinfo = entryName.replace(prefix, "");
                    return channelinfo
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (zipfile != null) {
                try {
                    zipfile!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return "default";
    }
}
