package dev.zero.tiplangpdam.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.model.User;
import dev.zero.tiplangpdam.model.response.Login;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_nip)
    EditText edtNip;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    ProgressDialog dialog;
    SessionManager sessionManager;
    String snip, spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        snip = edtNip.getText().toString().trim();
        spassword = edtPass.getText().toString().trim();
        if (!snip.isEmpty() && !spassword.isEmpty()) {
            doLogin();
        } else {
            Toast.makeText(LoginActivity.this, "Lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }
    private void doLogin() {
        dialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        dialog.setMessage("Authenticating..");
        dialog.show();

        ApiService.service_post.postLogin(snip, spassword).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                if(response.body().getCode() == 302){
                    dialog.dismiss();
                    User user = response.body().getData();
                    sessionManager.createLoginSession(user.getId(), user.getNama(),user.getNip(),user.getJabatan());
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "Gagal menghubung ke server", Toast.LENGTH_SHORT).show();
                Log.e("Login Auth", "onFailure: " + t.getMessage(), t);
            }
        });
    }
}
