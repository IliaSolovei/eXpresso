package dev.eSolovei.eXpresso.sample.android

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.eSolovei.eXpresso.sample.android.databinding.ActivityListBinding
import dev.eSolovei.eXpresso.sample.android.databinding.ListLayoutBinding
import dev.eSolovei.eXpresso.sample.android.databinding.MainCellBinding

class ListActivity : AppCompatActivity() {
    private lateinit var activityListBinding: ActivityListBinding
    private lateinit var listLayoutBinding: ListLayoutBinding

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityListBinding = ActivityListBinding.inflate(layoutInflater)
        listLayoutBinding = activityListBinding.includedListLayout
        val viewRoot = activityListBinding.root
        setContentView(viewRoot)
        setSupportActionBar(activityListBinding.toolbar)

        val values = (1..99).map {
            "${it}"
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(values) {
            if (it == "1" || it == "99") {
                finish()
            }
        }

        listLayoutBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}

internal typealias OnItemClickListener = (String) -> Unit

class MyAdapter(private val myDataset: List<String>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_cell, parent, false) as TextView


        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val text = myDataset[position]
        holder.textView.text = text
        holder.textView.setOnClickListener {
            itemClickListener(text)
        }
        holder.textView.contentDescription = text
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}
