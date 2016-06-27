package com.buris.cadastrodecliente;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Criando tabelas no sqlite
		SQLiteDatabase db = openOrCreateDatabase("cliente.db",
				Context.MODE_PRIVATE, null);

		StringBuilder sqlClentes = new StringBuilder();
		sqlClentes.append("CREATE TABLE IF NOT EXISTS cliente(");
		sqlClentes.append("_id INTEGER PRIMARY KEY, ");
		sqlClentes.append("nome VARCHAR(30), ");
		sqlClentes.append("email VARCHAR(30), ");
		sqlClentes.append("cidade VARCHAR(30));");

		db.execSQL(sqlClentes.toString());
		// fim da criacao do sqlite

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Metodo de insercao de dados
	public void metodoCadastrar(View v) {
		EditText txtNome = (EditText) findViewById(R.id.txtNome);
		EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
		EditText txtCidade = (EditText) findViewById(R.id.txtCidade);

		if (txtNome.getText().toString().length() <= 0) {
			txtNome.setError("Preencher o campo. ");
			txtNome.requestFocus();
		} else {
			try {

				SQLiteDatabase db = openOrCreateDatabase("cliente.db",
						Context.MODE_PRIVATE, null);
				// Adiciona as informacoes no ContentValues para inserir
				ContentValues ctv = new ContentValues();
				ctv.put("nome", txtNome.getText().toString());
				ctv.put("email", txtEmail.getText().toString());
				ctv.put("cidade", txtCidade.getText().toString());

				if (db.insert("cliente", "_id", ctv) > 0) {
					// Envia uma menssagem na tela
					Toast.makeText(getBaseContext(), "Cadastrado",
							Toast.LENGTH_SHORT).show();
					db.close();
				} else {
					// Envia uma menssagem na tela
					Toast.makeText(getBaseContext(), "Erro no ccadastro",
							Toast.LENGTH_SHORT).show();
					db.close();

				}

			} catch (Exception ex) {
				Toast.makeText(getBaseContext(), ex.getMessage(),
						Toast.LENGTH_SHORT).show();

			}
		}
	}

	public void getListActivity(View v) {
		Intent it = new Intent(getBaseContext(), ListaActivity.class);
		startActivity(it);

	}

}
