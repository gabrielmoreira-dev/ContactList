package br.edu.scl.ifsp.sdm.contactlist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.OnClickListener
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.ActivityContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.sdm.contactlist.model.Constant.EXTRA_VIEW_CONTACT
import br.edu.scl.ifsp.sdm.contactlist.model.Contact

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_details)

        setupContact()
        acb.saveBt.setOnClickListener { onSave() }
    }

    private fun setupContact() = (intent.getParcelableExtra<Contact>(EXTRA_CONTACT))?.let {
        intent.getBooleanExtra(EXTRA_VIEW_CONTACT, false).let {
            if (it) disableEditMode()
        }
        with(acb) {
            nameEt.setText(it.name)
            addressEt.setText(it.address)
            phoneEt.setText(it.phone)
            emailEt.setText(it.email)
        }
    }

    private fun disableEditMode() = with(acb) {
        nameEt.isEnabled = false
        addressEt.isEnabled = false
        phoneEt.isEnabled = false
        emailEt.isEnabled = false
        saveBt.visibility = GONE
    }

    private fun onSave() = Intent().apply {
        val id = (intent.getParcelableExtra<Contact>(EXTRA_CONTACT))?.id
        putExtra(EXTRA_CONTACT, getContact(id))
        setResult(RESULT_OK, this)
        finish()
    }

    private fun getContact(id: Int?) = with(acb) {
        Contact(
            id =  id ?: hashCode(),
            name = nameEt.text.toString(),
            address = addressEt.text.toString(),
            phone = phoneEt.text.toString(),
            email = emailEt.text.toString()
        )
    }
}