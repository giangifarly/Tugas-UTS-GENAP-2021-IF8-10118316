package com.studio39.catatanharian_10118316.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.studio39.catatanharian_10118316.R;
import com.studio39.catatanharian_10118316.dao.CatatanDAO;
import com.studio39.catatanharian_10118316.database.table.TableCatatan;
import com.studio39.catatanharian_10118316.model.Catatan;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteFragment extends Fragment {

    //Tanggal Pengerjaan 5 Juni 2021, 10118316, Gian Gifarly, IF8

    EditText editJudul, editKategori, editIsiCatatan;
    Button btnSave, btnDelete;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoteFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        editJudul = (EditText)view.findViewById(R.id.editJudul);
        editKategori = (EditText)view.findViewById(R.id.editKategori);
        editIsiCatatan = (EditText)view.findViewById(R.id.editCatatan);
        btnSave = (Button) view.findViewById(R.id.action_save1);
        btnDelete = (Button) view.findViewById(R.id.action_delete1);

        if (getArguments() == null){
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCatatan();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCatatan();
            }
        });

        if (getArguments() != null){
            recoveryCatatan();
        }
        setHasOptionsMenu(true);

        return view;
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
        }popThisFragments();
    }

    public void deleteCatatan(){
        CatatanDAO catatanDAO = new CatatanDAO(getActivity());
        catatanDAO.delete(getArguments().getInt(TableCatatan.FIELD_ID));
        popThisFragments();
    }

    private void popThisFragments(){
        recoveryCatatan();
        getActivity().getSupportFragmentManager().popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}