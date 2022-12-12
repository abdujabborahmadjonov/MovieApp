package dev.abdujabbor.home_rv_13.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.abdujabbor.home_rv_13.MySharedpreferances
import dev.abdujabbor.home_rv_13.databinding.ItemViewBinding
import dev.abdujabbor.home_rv_13.models.MyList

class MyRVAdapter(var list: ArrayList<MyList>,var rvClick: RvClick): RecyclerView.Adapter<MyRVAdapter.VH>() {
    inner class VH(var itemViewBinding: ItemViewBinding):RecyclerView.ViewHolder(itemViewBinding.root){
           fun onBind(list:MyList, position: Int){
             itemViewBinding.tvName.text = list.name
             itemViewBinding.tvInfo.text = list.author
             itemViewBinding.tvDate.text = list.date
               itemViewBinding.btnDelete.setOnClickListener {
                   rvClick.deleteMovie(list, position)
               }

               itemViewBinding.btnEdit.setOnClickListener {
                   rvClick.editMovie(list,position)
               }

           }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
          return holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface RvClick{
    fun deleteMovie(moview:MyList,position: Int)
    fun editMovie(moview:MyList,position: Int)
}