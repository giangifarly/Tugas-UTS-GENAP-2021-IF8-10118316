package com.studio39.catatanharian_10118316.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.studio39.catatanharian_10118316.R;
import com.studio39.catatanharian_10118316.dao.CatatanDAO;
import com.studio39.catatanharian_10118316.database.table.TableCatatan;
import com.studio39.catatanharian_10118316.model.Catatan;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    //Tanggal Pengerjaan 5 Juni 2021, 10118316, Gian Gifarly, IF8

    ListView ListView01;
    ArrayList<Catatan> catatans = new ArrayList<>();
    FloatingActionButton actionButton;
    EditText editJudul, editKategori, editIsiCatatan;
    Button btnSave, btnDelete;
    AlertDialog.Builder form;
    LayoutInflater inflate;
    View formView;


    public HomeFragment(){

    }

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, ListView01, false);
        ViewPager mPager = (ViewPager) view.findViewById(R.id.pager);
        actionButton = (FloatingActionButton) view.findViewById(R.id.fab);

        editJudul       = (EditText)view.findViewById(R.id.editJudul);
        editKategori    = (EditText)view.findViewById(R.id.editKategori);
        editIsiCatatan  = (EditText)view.findViewById(R.id.editCatatan);
        btnSave         = (Button) view.findViewById(R.id.action_save1);
        btnDelete       = (Button) view.findViewById(R.id.action_delete1);

        //start listeners

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.fragmentNote);
            }
        });
        ListView01 = (ListView) view.findViewById(R.id.listView);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Catatan catatan = catatans.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt(TableCatatan.FIELD_ID, catatan.getId());
                Navigation.findNavController(view).navigate(R.id.fragmentNote, bundle);
                //replaceFragment(bundle);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        CatatanDAO catatanDAO = new CatatanDAO(getActivity());
        catatans = catatanDAO.select(null, null);
        ListView01.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, catatans));
        super.onResume();
    }

    private void FormCatatan(){
        form = new AlertDialog.Builder(getActivity());
        inflate = getLayoutInflater();
        formView = inflate.inflate(R.layout.form_catatan, null);

    }

    public void toProfile(View view){
        Navigation.findNavController(view).navigate(R.id.profileFragment);
    }

    private void replaceFragment(Bundle args){
        NoteFragment note = new NoteFragment();
        if (args != null){
            note.setArguments(args);
        }
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(this.getClass().getName())
                .replace(R.id.fragment,note)
                .commit();
    }

    private void recoveryCatatan(){
        Bundle args = getArguments();
        CatatanDAO catatanDAO = new CatatanDAO(getActivity());
        ArrayList<Catatan> catatans = catatanDAO.select(TableCatatan.FIELD_ID +"= ?", new String[]{String.valueOf(args.getInt(TableCatatan.FIELD_ID))});
        if (catatans.size() > 0){
            Catatan catatan = catatans.get(0);
            editJudul.setText(catatan.getJudul());
            editKategori.setText(catatan.getKategori());
            editIsiCatatan.setText(catatan.getIsi_catatan());
        }
    }

    private void saveCatatan(){
        CatatanDAO catatanDAO = new CatatanDAO(getActivity());
        Catatan catatan = new Catatan();
        catatan.setJudul(editJudul.getText().toString().trim());
        catatan.setKategori(editKategori.getText().toString().trim());
        catatan.setIsi_catatan(editIsiCatatan.getText().toString().trim());

        if (getArguments() != null){
            catatan.setId(getArguments().getInt(TableCatatan.FIELD_ID));
            catatanDAO.update(catatan);
        }else {
            catatanDAO.insert(catatan);
        }
    }

    public void deleteCatatan(){
        CatatanDAO catatanDAO = new CatatanDAO(getActivity());
        catatanDAO.delete(getArguments().getInt(TableCatatan.FIELD_ID));
    }

}