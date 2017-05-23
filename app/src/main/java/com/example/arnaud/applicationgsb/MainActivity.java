package com.example.arnaud.applicationgsb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText editNom;
    private EditText editGlycemie;
    private EditText editPrenom;
    private Button btnAfficher;
    private Button btnMAS;
    private Button btnAjouter;
    private RadioButton rdoProtocole1;
    private RadioButton rdoProtocole2;
    private TextView TxtVAfficherRes;
    private TextView txtPrenom;
    private TextView txtNom;
    private MaladesBDD Malade;



    public MainActivity(){

    }
    private static final int MYSECONDACTIVITY_REQUESTCODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editGlycemie = (EditText)findViewById(R.id.editGlycemie);
        editNom = (EditText)findViewById(R.id.editNom);
        editPrenom =(EditText)findViewById(R.id.editPrenom);
        btnAfficher = (Button) findViewById(R.id.btnAfficher);
        btnMAS = (Button) findViewById(R.id.btnMAS);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        rdoProtocole1= (RadioButton)findViewById(R.id.rdoProtocole1);
        rdoProtocole2= (RadioButton)findViewById(R.id.rdoProtocole2);
        TxtVAfficherRes =(TextView)findViewById(R.id.TxtVAfficherRes);
        txtPrenom =(TextView)findViewById(R.id.txtPrenom);
        txtNom =(TextView)findViewById(R.id.txtNom);
        btnAfficher.setOnClickListener(clickListenerBtnAfficher);
        Malade = new MaladesBDD(MainActivity.this);
        Malade.open();


        btnMAS.setOnClickListener(clickListenerBtnMaladeSignaler);
        btnAjouter.setOnClickListener(clickListenerbtnAjouter);


    }
    private View.OnClickListener clickListenerBtnAfficher = new View.OnClickListener(){
        public void onClick(View arg0){
        MapProtocoles m = new MapProtocoles();
            m.initialiser();


            if (! (rdoProtocole1.isChecked())&& !(rdoProtocole2.isChecked())){
                Toast.makeText(MainActivity.this,"Vous n'avez pas choisi le protocole !",Toast.LENGTH_LONG).show();

            }
            else if(editGlycemie.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this,"Vous n'avez pas saisi la glycémie!",Toast.LENGTH_LONG).show();

            }

            else if (rdoProtocole1.isChecked()){
              Protocole p = m.getProtocole(1);
                double g =Double.parseDouble(editGlycemie.getText().toString());
                int i = p.insuline(g);
                TxtVAfficherRes.setText(String.valueOf(i));

            }
            else if(rdoProtocole2.isChecked()){
               Protocole p = m.getProtocole(2);
                double g = Double.parseDouble(editGlycemie.getText().toString());
                int i = p.insuline(g);
                TxtVAfficherRes.setText(String.valueOf(i));
            }

            if(Double.parseDouble(editGlycemie.getText().toString())>=2){
                editNom.setVisibility(View.VISIBLE);
                editPrenom.setVisibility(View.VISIBLE);
                txtPrenom.setVisibility(View.VISIBLE);
                txtNom.setVisibility(View.VISIBLE);
                btnAjouter.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"Vous devez ajouter le malade !", Toast.LENGTH_LONG).show();


            }

        }



    };
    private View.OnClickListener clickListenerbtnAjouter = new View.OnClickListener(){

        Malade malade;

        public void onClick(View v) {
            String prenom = editPrenom.getText().toString();
            String nom = editNom.getText().toString();
            Double glycemie = Double.parseDouble(TxtVAfficherRes.getText().toString());
            if (prenom.isEmpty() || nom.isEmpty())
                Toast.makeText(MainActivity.this,"Vous devez remplir tous les champs !", Toast.LENGTH_LONG);
            else {
                malade = new Malade(prenom,nom,glycemie);
                long ret = Malade.ajoutMalade(malade);
                if(ret != -1)
                    Toast.makeText(MainActivity.this,"Ajout effectué", Toast.LENGTH_LONG);
                else
                    Toast.makeText(MainActivity.this,"Ajout non effectué",Toast.LENGTH_LONG);
            }

        }



    };
    private View.OnClickListener clickListenerBtnMaladeSignaler = new View.OnClickListener() {
       @Override
        public void onClick(View view) {
            Intent it = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(it, MYSECONDACTIVITY_REQUESTCODE);

        }
    };

}
