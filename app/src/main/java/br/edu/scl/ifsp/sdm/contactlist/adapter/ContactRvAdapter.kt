package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact
import br.edu.scl.ifsp.sdm.contactlist.view.OnContactClickListener

class ContactRvAdapter(
    private val contactList: MutableList<Contact>,
    private val clickListener: OnContactClickListener
): RecyclerView.Adapter<ContactRvAdapter.ContactViewHolder>() {
    override fun getItemCount() = contactList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TileContactBinding
        .inflate(LayoutInflater.from(parent.context), parent, false).run {
            ContactViewHolder(this)
        }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = with(holder) {
        nameTv.text = contactList[position].name
        emailTv.text = contactList[position].email
        phoneTv.text = contactList[position].phone
    }

    inner class ContactViewHolder(
        tileContactBinding: TileContactBinding
    ) : RecyclerView.ViewHolder(tileContactBinding.root) {
        val nameTv = tileContactBinding.nameTv
        val emailTv = tileContactBinding.emailTv
        var phoneTv = tileContactBinding.phoneTv

        init {
            tileContactBinding.root.apply {
                setOnCreateContextMenuListener { menu, _, _ -> onCreateContextMenuListener(menu) }
                setOnClickListener { onClickListener() }
            }
        }

        private fun onCreateContextMenuListener(menu: ContextMenu) {
            (clickListener as AppCompatActivity).menuInflater.inflate(
                R.menu.context_menu_main,
                menu
            )
            menu.findItem(R.id.removeContactMi).setOnMenuItemClickListener {
                clickListener.onRemoveContactClick(adapterPosition)
                true
            }
            menu.findItem(R.id.editContactMi).setOnMenuItemClickListener {
                clickListener.onEditContactClick(adapterPosition)
                true
            }
        }

        private fun onClickListener() {
            clickListener.onContactClick(adapterPosition)
        }
    }
}