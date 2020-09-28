package com.example.madlevel3task2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.fragment_overview.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OverviewFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals, { partItem : Portal -> portalItemClicked(partItem) });
    //private lateinit var portalAdapter: PortalAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews();

        observeAddPortalResult();
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) {
                key, bundle -> bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                portals.add(it);
                portalAdapter.notifyDataSetChanged();
            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!");
        }
    }

    private fun initViews() {
        rv_portals.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        /** Biggest change, updating the adapter to call the method when clicked*/
        rv_portals.adapter = portalAdapter;
        rv_portals.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        portals.add(Portal("VLO", "http://vlo.informatica.hva.nl"));
        portals.add(Portal("Roosters", "http://roosters.hva.nl"));
        portals.add(Portal("Sis", "http://sis.hva.nl"));
        portals.add(Portal("HvA", "http://hva.nl"));

    }

    private fun portalItemClicked(portalItem: Portal) {
        //Toast.makeText(context, "Clicked: ${portalItem.titleText}", Toast.LENGTH_LONG).show();
        /** Seems to be the quickest way to open a custom tab for the url.*/
        val builder = CustomTabsIntent.Builder();
        /** Couldn't find a way to refer to the context without the ? that also worked, getColor only functions without ?*/
        //builder.setToolbarColor((ContextCompat.getColor(activity?.applicationContext, R.color.colorPrimary)));
        builder.addDefaultShareMenuItem();
        builder.setShowTitle(true);

        val customTabsIntent  = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(portalItem.urlText));
    }
}