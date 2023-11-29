package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact

class ContactAdapter(
    context: Context,
    private val contactList: MutableList<Contact>
): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contactTileView = convertView ?: inflateContactTile(parent)

        (contactTileView.tag as TileContactHolder).apply {
            nameTv.text = contactList[position].name
            emailTv.text = contactList[position].email
        }

        return contactTileView
    }

    private fun inflateContactTile(parent: ViewGroup): View {
        TileContactBinding.inflate(
            context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            parent,
            false
        ).let {
            val contactTileView = it.root
            contactTileView.tag = TileContactHolder(it.nameTv, it.emailTv)
            return contactTileView
        }
    }

    private data class TileContactHolder(val nameTv: TextView, val emailTv: TextView)
}