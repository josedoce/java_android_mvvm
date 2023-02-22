package com.example.mvvm.viewmodels;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvm.BR;
import com.example.mvvm.model.Usuario;

//para usar o padrão mvvn é necessário que configure o build.gradle assim:
//android {
//
//    dataBinding {
//        enabled = true
//    }
//}
//alem disso, é necessário importar a seguinte biblioteca:
//implementation 'android.arch.lifecycle:extensions:1.1.0'
public class MainViewModel extends BaseObservable {
    //model
    private Usuario usuario;

    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";

    public MainViewModel(){
        this.usuario = new Usuario("",22, "");
    }

    @Bindable
    private String toastMessage = null;



    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }


    public String getToastMessage() {
        return toastMessage;
    }


    @Bindable
    public String getUserEmail(){
        return usuario.getNome();
    }

    public void setUserEmail(String email) {
        usuario.setNome(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserPassword(){
        return usuario.getSenha();
    }
    public void setUserPassword(String password) {
        usuario.setSenha(password);
        notifyPropertyChanged(BR.userPassword);
    }
    public void onLoginClicked() {
        System.out.println("O email: "+getUserEmail());
        if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches() && getUserPassword().length() > 5;
    }
}
