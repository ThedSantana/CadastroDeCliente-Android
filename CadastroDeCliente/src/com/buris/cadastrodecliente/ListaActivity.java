package com.buris.cadastrodecliente;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class ListaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);

		SQLiteDatabase db = openOrCreateDatabase("cliente.db",
				Context.MODE_PRIVATE, null);

		Cursor cursor = db.rawQuery("SELECT * FROM cliente", null);

		String[] from = { "_id", "nome", "email" };
		int[] to = { R.id.textID, R.id.textNome, R.id.textEmail };

		android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(
				getBaseContext(), R.layout.listar_model, cursor, from, to);

		ListView lvDados = (ListView) findViewById(R.id.listDadosCliente);

		lvDados.setAdapter(ad);
		db.close();
	}
}
