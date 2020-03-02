package cat.villalba.projectem7_david_raul.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.fragments.chatsFragment;
import cat.villalba.projectem7_david_raul.fragments.usersFragment;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragmento(new chatsFragment(),getString(R.string.xat));
        viewPagerAdapter.addFragmento(new usersFragment(), getString(R.string.contactes));

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragmentos;
        private ArrayList<String> nombres;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragmentos = new ArrayList<>();
            this.nombres = new ArrayList<>();

        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragmento(Fragment fragmento, String nombre) {
            fragmentos.add(fragmento);
            nombres.add(nombre);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return nombres.get(position);
        }
    }
}
