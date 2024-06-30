package com.petworld.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.petworld.R
import com.petworld.databinding.FragmentHomeBinding
import com.petworld.databinding.PetListItemBinding
import com.petworld.model.PetWorld
import com.squareup.picasso.Picasso

class PetListAdapter(val petList:ArrayList<PetWorld>):RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {
    class PetViewHolder(var binding: PetListItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PetListItemBinding.inflate(inflater,parent,false)
        return PetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return petList.size
    }


    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val petWorld = petList[position]
        holder.binding.petWorld = petWorld

            holder.binding.clickListener = View.OnClickListener{
                val id = petWorld.id.toString()
                val imgUrl = petWorld.url.toString()
                val user = "@${petWorld.user}"
                val action = HomeFragmentDirections.actionDetailFragment(id,imgUrl,user)
                Navigation.findNavController(it).navigate(action)
            }
        Picasso.get()
            .load(petWorld.url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imgBerita)

        holder.binding.executePendingBindings()


        /*
        holder.binding.txtUser.text = "@"+this
        holder.binding.txtShortPara.text = this
        holder.binding.txtCategoryHewan.text = petList[position].catergory
        var url = petList[position].url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace() }
        builder.build().load(url).into(holder.binding.imgBerita)
        holder.binding.btnRead.setOnClickListener{
            val idBerita = petList[position].id.toString()
            val imgURL = petList[position].url.toString()
            val user = "@"+petList[position].user.toString()
            val action = HomeFragmentDirections.actionDetailFragment(idBerita, imgURL, user)
            Navigation.findNavController(it).navigate(action)
        }*/
    }
    fun updatePetList(newPetList: List<PetWorld>) {
        petList.clear()
        petList.addAll(newPetList)
        notifyDataSetChanged()
    }
}