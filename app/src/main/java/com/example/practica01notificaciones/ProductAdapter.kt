import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica01notificaciones.Product
import com.example.practica01notificaciones.ProductDetailActivity
import com.example.practica01notificaciones.R

class ProductAdapter(private val productList: List<Product>, private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.txtProductTitle)
        val priceTextView: TextView = view.findViewById(R.id.txtProductPrice)
        val descriptionTextView: TextView = view.findViewById(R.id.txtProductDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.titleTextView.text = product.title
        holder.priceTextView.text = product.price
        holder.descriptionTextView.text = product.description
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("title", product.title)
            intent.putExtra("price", product.price)
            intent.putExtra("description", product.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = productList.size
}
