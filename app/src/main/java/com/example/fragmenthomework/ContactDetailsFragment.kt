package com.example.fragmenthomework

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import coil.load
import com.example.fragmenthomework.databinding.FragmentContactDetailsBinding


class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var contact: Contact

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

        contact = requireArguments().getParcelable("contact")!!
        val imageUrl = "https://loremflickr.com/320/240/${contact.id}"
        binding.imageView.load(imageUrl)
        binding.editTextFirstName.text =
            Editable.Factory.getInstance().newEditable(contact.firstName)
        binding.editTextLastName.text = Editable.Factory.getInstance().newEditable(contact.lastName)
        binding.editTextNumber.text = Editable.Factory.getInstance().newEditable(contact.number)
        binding.saveButton.setOnClickListener {
            contact.firstName = binding.editTextFirstName.text.toString()
            contact.lastName = binding.editTextLastName.text.toString()
            contact.number = binding.editTextNumber.text.toString()
            val bundle = bundleOf("message" to contact)
            setFragmentResult("requestKey", bundle)
            parentFragmentManager.popBackStack()

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