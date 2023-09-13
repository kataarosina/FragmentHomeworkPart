package com.example.fragmenthomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmenthomework.databinding.FragmentContactDetailsBinding



class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact = requireArguments().getParcelable<Contact>("contact")
        if (contact != null) {
            binding.textViewName.text = contact.name
            binding.textViewNumber.text = contact.number

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(contact: Contact): ContactDetailsFragment {
            val fragment = ContactDetailsFragment()
            val args = Bundle()
            args.putParcelable("contact", contact)
            fragment.arguments = args
            return fragment
        }
    }
}






