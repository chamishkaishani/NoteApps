import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorViewModel : ViewModel() {
    val backgroundColor = MutableLiveData<Int>()

    init {
        // Initialize background color to white
        backgroundColor.value = Color.WHITE
    }

    fun changeBackgroundColor(color: Int) {
        backgroundColor.value = color
    }
}
