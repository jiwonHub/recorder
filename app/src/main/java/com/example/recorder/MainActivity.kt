package com.example.recorder

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val recordButton: RecordButton by lazy{
        findViewById(R.id.recordButton)
    }
    private val requiredPermissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    private var state = State.BEFORE_RECORDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAudioPermission()
        initViews()
    }

    override fun onRequestPermissionsResult( // 요청한 권한에 대한 결과
        requestCode: Int, // 권한 요청 코드
        permissions: Array<out String>,
        grantResults: IntArray // 요청한 권한에 대한 결과 array
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if(!audioRecordPermissionGranted){
            finish()
        }
    }

    @SuppressLint("NewApi")
    private fun requestAudioPermission(){
        requestPermissions(requiredPermissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    private fun initViews(){ // 상태에 따라 아이콘 변경
        recordButton.updateIconWithState(state)
    }

    companion object{
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }
}