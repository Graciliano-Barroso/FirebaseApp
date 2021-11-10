package com.cursoandroid.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    /* Criar usuário
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();*/
    /* Autenticar usuário
    private FirebaseAuth usuario = FirebaseAuth.getInstance();*/

    /* Faz o upload de imagem */
    private ImageView imageFoto;
    private Button buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Faz o upload de imagem
        imageFoto = findViewById(R.id.imageFoto);
        buttonUpload = findViewById(R.id.buttonUpload);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Configura para imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                //Recupera bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                //Comprime bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                //Converte o baos para pixel brutos em uma matriz de bytes
                // (dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                //Define nós pra o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                //Cria uma pasta no storage
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                ImageView imageFoto = findViewById(R.id.imageFoto);

                Glide.with(MainActivity.this).load(imagemRef).into(imageFoto);

                /*imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao deletar imagem: ", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this,
                                "Sucesso ao deletar imagem: ", Toast.LENGTH_LONG).show();
                    }
                });*/

                /*Nome da imagem
                String nomeArquivo = UUID.randomUUID().toString();*/
                //StorageReference imagemRef = imagens.child("celular.jpeg");

                //Retorna objeto que irá controlar o upload
                /*UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this,
                                "Upload da imagem falhou: " + e.getMessage()
                                        .toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        //Recupera URL
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Sucesso ao fazer upload: ", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                });*/

            }
        });

        //DatabaseReference usuarios = referencia.child("usuarios");

        //DatabaseReference usuarioPesquisa = usuarios.child("-MmIAwoMfdG72KfLj971");
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Graciliano");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);
        //Query usuarioPesquisa = usuarios.orderByKey().limitToLast(3);

        /* Mairor ou igual (>=)
        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40);*/

        /* Menor ou igual (<=)
        Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(40);*/

        /* Entre dois valores
        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(41);

        /* Filtrar palavras
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("G").endAt("G" + "\uf8ff");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                Log.i("Dados usuario", "nome: " + dadosUsuario.getNome() + " idade: " + dadosUsuario.getIdade());
                Log.i("Dados usuario", snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        /*
        Usuario usuario = new Usuario();
        usuario.setNome("Gabriel");
        usuario.setSobrenome("Braga");
        usuario.setIdade(5);

        usuarios.push().setValue(usuario);*/

        /*Deslogar usuário
        usuario.signOut();*/

        /*Logar usuário
        usuario.signInWithEmailAndPassword("gracilianobm@gmail.com", "gbm12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao logar usuário!");
                        }else{
                            Log.i("CreateUser", "Erro ao logar usuário!");

                        }
                    }
                });*/


        /*Verifica se o usuário está logado
        if (usuario.getCurrentUser() != null){
            Log.i("CurrentUser", "Usuário logado!");
        }else{
            Log.i("CurrentUser", "Usuário não está logado!");
        }*/



        /*Cadastro de usuário
        usuario.createUserWithEmailAndPassword("gracilianobm@gmail.com", "gbm12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                                Log.i("CreateUser", "Sucesso ao cadastrar usuário!");
                        }else{
                                Log.i("CreateUser", "Erro ao cadastrar usuário!");

                        }
                    }
                });*/

        /*
        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Usuario usuario = new Usuario();
        usuario.setNome("Graciliano");
        usuario.setSobrenome("Barroso");
        usuario.setIdade(41);

        //usuarios.child("001").setValue(usuario);

        DatabaseReference produtos = referencia.child("produtos");

        Produto produto = new Produto();
        produto.setDescricao("Inspiron 2 em 1");
        produto.setMarca("Dell");
        produto.setPreo(4.501);

        produtos.child("002").setValue(produto);*/

    }
}