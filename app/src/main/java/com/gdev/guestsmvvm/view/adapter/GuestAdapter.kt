package com.gdev.guestsmvvm.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdev.guestsmvvm.R
import com.gdev.guestsmvvm.databinding.ItemGuestBinding
import com.gdev.guestsmvvm.service.model.GuestModel
import com.gdev.guestsmvvm.view.listener.GuestListener

class GuestAdapter() : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    private lateinit var mBindingItem: ItemGuestBinding
    private lateinit var mGuestItens: List<GuestModel>
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        mBindingItem = ItemGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(mBindingItem, mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestItens[position])
    }

    override fun getItemCount() = mGuestItens.count()

    fun refreshList(itens: List<GuestModel>){
        mGuestItens = itens
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener){
        mListener = listener
    }

    class GuestViewHolder(binding: ItemGuestBinding, val listener: GuestListener) : RecyclerView.ViewHolder(binding.root){
        private val textName = binding.textGuetName

        fun bind(guest: GuestModel){
            textName.text = guest.name

            textName.setOnClickListener{
                listener.onClick(guest.id)
            }

            textName.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle(R.string.remocao_convidado)
                    .setMessage(R.string.deseja_remover)
                    .setPositiveButton(R.string.remover) { dialog, which ->
                        listener.onDelete(guest.id)
                    }
                    .setNegativeButton(R.string.cancelar, null)
                    .show()
                true
            }
        }
    }

}