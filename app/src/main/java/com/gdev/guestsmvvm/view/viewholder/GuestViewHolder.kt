//package com.gdev.guestsmvvm.view.viewholder
//
//import android.app.AlertDialog
//import android.content.DialogInterface
//import android.view.View
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.gdev.guestsmvvm.R
//import com.gdev.guestsmvvm.service.model.GuestModel
//import com.gdev.guestsmvvm.view.listener.GuestListener
//
//class GuestViewHolder(itemView: View, val listener: GuestListener) : RecyclerView.ViewHolder(itemView){
//    fun bind(guest:GuestModel){
//       val textName = itemView.findViewById<TextView>(R.id.text_guet_name)
//        textName.text = guest.name
//
//        textName.setOnClickListener{
//            listener.onClick(guest.id)
//        }
//
//        textName.setOnLongClickListener {
//            AlertDialog.Builder(itemView.context)
//                .setTitle(R.string.remocao_convidado)
//                .setMessage(R.string.deseja_remover)
//                .setPositiveButton(R.string.remover) { dialog, which ->
//                    listener.onDelete(guest.id)
//                }
//                .setNegativeButton(R.string.cancelar, null)
//                .show()
//            true
//        }
//    }
//}