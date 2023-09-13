package com.example.fragmenthomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmenthomework.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactAdapter: ContactAdapter

    private val contacts: MutableList<Contact> = ContactsList.contacts



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("gdfgdg", "gdfgdfg")
        contactAdapter = ContactAdapter(contacts) { contact ->
            openContactDetails(contact)
        }


        setFragmentResultListener("requestKey") { _, bundle ->
            val message = bundle.getParcelable<Contact>("message")
            if (message != null) {
                updateContact(message)
                Toast.makeText(
                    requireContext(),
                    "Changes saved",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = contactAdapter

    }

    private fun openContactDetails(contact: Contact) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, ContactDetailsFragment.newInstance(contact))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Функция для обновления контакта в списке
    fun updateContact(updatedContact: Contact) {
        val position = contacts.indexOfFirst { it.firstName == updatedContact.firstName }
        if (position != -1) {
            contacts[position] = updatedContact
            contactAdapter.notifyItemChanged(position)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}