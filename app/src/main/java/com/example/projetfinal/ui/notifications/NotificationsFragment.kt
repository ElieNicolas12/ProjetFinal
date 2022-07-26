package com.example.projetfinal.ui.notifications

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.projetfinal.Model.LatLong
import com.example.projetfinal.Model.TempStore
import com.example.projetfinal.R
import com.example.projetfinal.databinding.FragmentNotificationsBinding
import com.example.projetfinal.ui.home.HomeFragment
import com.google.android.gms.maps.model.LatLng

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    lateinit var searchadapter: ArrayAdapter<*>
    lateinit var lv_listView: ListView
    lateinit var tv_emptyView:TextView
    lateinit var sv_searchView:SearchView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tv_emptyView=binding.tvEmptyTextView
        lv_listView=binding.lvListView
        sv_searchView=binding.idSV
        searchadapter= ArrayAdapter(this.context!!,android.R.layout.simple_list_item_1,resources.getStringArray(
            R.array.countries_array))
        lv_listView.adapter=searchadapter


        sv_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (resources.getStringArray(R.array.countries_array).contains(query)) {
                    searchadapter.filter.filter(query)
                } else {
                    Toast.makeText(context, "No Language found..", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchadapter.filter.filter(newText)
                return false
            }
        })
        lv_listView.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this.context,parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
//            TempStore.Case=1
//            LatLong.city=(parent?.getItemAtPosition(position).toString())
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.nav_host_fragment_activity_main,HomeFragment())
//            transaction?.setReorderingAllowed(true)
//            transaction?.addToBackStack(null)
//
//            transaction?.commit()




        }
        lv_listView.emptyView=tv_emptyView
        return root
    }
}

