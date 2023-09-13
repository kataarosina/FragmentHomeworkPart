package com.example.fragmenthomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmenthomework.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {


    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contacts = listOf(
            Contact("John Doe", "123-456-7890"),
            Contact("Jane Smith", "987-654-3210"),
            Contact("Alice Johnson", "555-123-4567"),
            Contact("Bob Brown", "111-222-3333"),
            Contact("Eva Williams", "999-888-7777"),
            Contact("Michael Davis", "444-555-6666")

        )

        contactAdapter = ContactAdapter(contacts) { contact ->
            // Обработка нажатия на элемент списка
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, ContactDetailsFragment.newInstance(contact))
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contactAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}