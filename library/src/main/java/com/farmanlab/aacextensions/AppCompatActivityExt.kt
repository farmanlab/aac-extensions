import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> VM): VM =
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
            return requireNotNull(modelClass.cast(factory()))
        }
    }).get(VM::class.java)
