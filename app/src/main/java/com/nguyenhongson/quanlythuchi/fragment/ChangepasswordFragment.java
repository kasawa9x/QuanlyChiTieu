package com.nguyenhongson.quanlythuchi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.nguyenhongson.quanlythuchi.R;
import com.nguyenhongson.quanlythuchi.database.DaoTaiKhoan;
import com.nguyenhongson.quanlythuchi.model.TaikhoanMatKhau;

import java.util.ArrayList;

public class ChangepasswordFragment extends Fragment {
    EditText txtCTk, txtCpass, txtNewPass;
    Button btChangePass, btNhapLai;
    DaoTaiKhoan tkDao;
    Animation animation;
    LinearLayout linearLayout;
    ArrayList<TaikhoanMatKhau> listTk = new ArrayList<>();
    View view;
    public ChangepasswordFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_changepassword, container, false);
        init();

        btChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean xetTk = false, xetMk = true;
                tkDao = new DaoTaiKhoan(getContext());
                String tk = txtCTk.getText().toString();
                String mk = txtCpass.getText().toString();
                String mkk = txtNewPass.getText().toString();
                TaikhoanMatKhau tkmkMoi = new TaikhoanMatKhau(tk,mkk);
                listTk = tkDao.getALl();

                for (int i = 0; i < listTk.size(); i++) {
                    TaikhoanMatKhau tkx = listTk.get(i);
                    if (tkx.getTenTaiKhoan().matches(tk)&&tkx.getMatKhau().matches(mk)) {
                        xetTk = true;
                        break;
                    }
                }

                if(mk.matches(mkk)){
                    xetMk=false;
                }
                else {
                    xetMk=true;
                }

                if (tk.isEmpty()) {
                    Toast.makeText(getContext(), "T??n t??i kho???n kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mk.isEmpty() || mkk.isEmpty()) {
                        Toast.makeText(getContext(), "M???t kh???u kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (xetTk == true) {
                            if (xetMk == true) {
                                tkDao.doiMk(tkmkMoi);
                                Toast.makeText(getContext(), "?????i m???t kh???u th??nh c??ng!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "M???t kh???u c?? kh??ng ???????c tr??ng v???i m???t kh???u m???i!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "T??n t??i kho???n ho???c m???t kh???u kh??ng ????ng!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCTk.setText("");
                txtCpass.setText("");
                txtNewPass.setText("");
            }
        });
        return view;
    }
    private void init(){
        txtCTk = view.findViewById(R.id.edtCUser);
        txtCpass = view.findViewById(R.id.edtCPass);
        txtNewPass = view.findViewById(R.id.edtNewPass);
        btChangePass  =view.findViewById(R.id.btnChange);
        btNhapLai = view.findViewById(R.id.btnRelay);
        linearLayout=view.findViewById(R.id.linearLayoutchange);
    }

}
