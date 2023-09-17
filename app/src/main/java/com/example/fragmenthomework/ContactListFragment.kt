package com.example.fragmenthomework

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmenthomework.databinding.FragmentContactListBinding
import com.example.fragmenthomework.util.addCardDecoration

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactAdapter: ContactAdapter

    private val contacts: MutableList<Contact> = ContactsList.contacts

    private var searchQuery: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactAdapter = ContactAdapter(requireContext(),
            { contact -> openContactDetails(contact) },
            { contact -> showDeleteContactDialog(contact) })

        val searchEditText = binding.editTextSearch
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                searchQuery = s.toString().trim()
                filterContacts()
            }
        })


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
        contactAdapter.submitList(contacts)
        binding.recyclerView.addCardDecoration(70)

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

    private fun showDeleteContactDialog(contact: Contact) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Deleting the contact")
        alertDialog.setMessage("Are you sure you'd like to delite the contact ${contact.firstName} ${contact.lastName}?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            deleteContact(contact)
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    private fun filterContacts() {
        val filteredContacts = if (searchQuery.isEmpty()) {
            contacts
        } else {
            contacts.filter { it.lastName.contains(searchQuery, ignoreCase = true) }
        }
        contactAdapter.submitList(filteredContacts)
    }

    private fun deleteContact(contact: Contact) {
        contacts.remove(contact)
        contactAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}