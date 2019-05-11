package com.ucsdextandroid1.musicsearch.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.ucsdextandroid1.musicsearch.R;
import com.ucsdextandroid1.musicsearch.data.DataSources;
import com.ucsdextandroid1.musicsearch.data.SongItem;
import com.ucsdextandroid1.musicsearch.utils.Debouncer;

import java.util.Collections;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    private SearchAdapter searchAdapter;
    private String latestSearchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchAdapter = new SearchAdapter();

        RecyclerView recyclerView = findViewById(R.id.am_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        EditText editText = findViewById(R.id.am_toolbar_edit_text);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                debouncer.onChange(s.toString());
            }
        });

        searchAdapter.setOnItemClickListener(new OnItemClickListener<SongItem>() {
            @Override
            public void onItemClicked(SongItem item) {
                Intent intent = new Intent(SearchActivity.this, MusicDetailsActivity.class);
                intent.putExtra("song", item);

                startActivity(intent);
            }
        });
    }



    /**
     * Debouncer keeps us from making a bunch of api calls if the text changes too quickly. We will
     * make at most one search every 200 milliseconds even if the text changes faster than that.
     */
    private Debouncer<String> debouncer = Debouncer.create(200, new Debouncer.ChangeListener<String>() {
        @Override
        public void onChange(String item) {
            performSearch(item);
        }
    });

    private void performSearch(String term) {
        String trimmedTerm = term.trim();

        if(!Objects.equals(trimmedTerm, latestSearchTerm)) {
            latestSearchTerm = trimmedTerm;
            if(TextUtils.isEmpty(trimmedTerm)) {
                searchAdapter.submitList(Collections.emptyList());
            }
            else {
                DataSources.getInstance().search(trimmedTerm, data -> {
                    if(!TextUtils.isEmpty(latestSearchTerm))
                        searchAdapter.submitList(data);
                });
            }
        }
    }
}
