package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*
import kotlinx.android.synthetic.main.item_portal.*


const val ARG_PORTAL_CLASS = "arg_portal_class"
const val REQ_PORTAL_KEY = "req_portal_key"
const val BUNDLE_PORTAL_KEY = "bundle_portal_key"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_add_portal.setOnClickListener {
            onAddPortal();
        }
    }

    private fun onAddPortal() {
        var newPortal = Portal(et_title.text.toString(), et_url.text.toString());
        //var args = Bundle();
        //args.putParcelable(ARG_PORTAL_CLASS, newPortal);

        setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, newPortal)))

        findNavController().navigate(R.id.action_addPortalFragment_to_overviewFragment);
        //findNavController().popBackStack();
    }
}