package com.example.olamundo20

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.olamundo20.databinding.ActivityHomeBinding
import com.example.olamundo20.domain.ChronometerViewModel
import com.example.olamundo20.domain.LiveDataTimerViewModel
import com.example.olamundo20.domain.Usuario
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File
import androidx.core.content.FileProvider
import kotlin.io.path.pathString


class HomeActivity : LogActivity() {
    lateinit var binding : ActivityHomeBinding

    //caminho para salvar arquivo
    var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val args = intent.extras
        val user = args?.getSerializable("usuario") as Usuario
        tNome.text = "Olá, ${user.nome}"

        btAbrirCamera.setOnClickListener{
            onClickCamera()
        }
        if (savedInstanceState != null) {
            // se girou a tela recupera o estado
            file = savedInstanceState.getSerializable("file") as File
            showImage(file)
        }
        //valida a permissão para a câmera
        val ok = PermissionUtils.request(this, arrayOf(android.Manifest.permission.CAMERA))
        if (!ok) {
            //deixa o botão invisível
            btAbrirCamera.visibility = View.INVISIBLE
        }
//        //live data
//        val mLiveDataTimerViewModel: LiveDataTimerViewModel = ViewModelProvider(this).get(
//            LiveDataTimerViewModel :: class.java)
//        subscribe(mLiveDataTimerViewModel)

//        //permissão de localização
//        val permissionLocation = PermissionUtils.request(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
//        if (permissionLocation){
//            this.bindLocationListener()
//        }

    }
    //Criar um arquivo no sdcard privado do app
    //a câmera vai salvar a foto no caminho deste arquivo
    fun creativeFile(fileName: String): File {
        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (sdCardDir != null && sdCardDir.exists()) {
            sdCardDir.mkdir()
        }
        val file = File(sdCardDir, fileName)
        //Salva file como atributo para salvar estado caso gire a tela
        this.file = file
        return file
    }
    private fun onClickCamera() {
        //(*1*) Cria o caminho do arquivo no sdcard
        val file = kotlin.io.path.createTempFile("foto.jpg")
        log("Camera file: $file")
        //Chama a intent informado o arquivo para salvar a foto
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //ERRO!
        val uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider", file
        )
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        // Chama a intent de captura de foto
        startActivityForResult(i, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            //(*4*) se a câmera retornou, vamos mostrar o arquivo da foto
            showImage(file)
        }
    }
    //Atualiza a imagem na tela
    private fun showImage(file: File?) {
        if (file != null && file.exists()) {
            //(*5*) redimensiona a imagem para o tamanho do Image View
            val bitmap = ImageUtils.resize(file, 1200, 800)
            log("img1: w/h" + imgFoto.width + "/" + imgFoto.height)
            log("img2: w/h" + bitmap.width + "/" + bitmap.height)
            log("file:" + file)
            //(*6*) Mostrar a foto no Image View
            imgFoto.setImageBitmap(bitmap)
        }
    }
    fun log (s:String) {
        Log.d("android", s)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        //(*3*) Salvar o estado caso gire a tela
        outState.putSerializable("file", file)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val ok = PermissionUtils.validate(this, permissions as Array<String>)
        if(ok) {
            //Deixa o botão visivel se o usuário aceitou a permissão
            btAbrirCamera.visibility = View.VISIBLE
        }
    }
    //live data
//    fun subscribe(mLiveDataTimerViewModel: LiveDataTimerViewModel) {
//        val elapsedTimeObserver = Observer<Log>{ newText ->
//            this getResources().getString( R.String.seconds, newText)
//            findViewById<TextView>(R.id.timerTextView).setText(newText)
//        }
//        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
//    }

}