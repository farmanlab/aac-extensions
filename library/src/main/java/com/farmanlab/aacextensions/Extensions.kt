import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

// region ViewModel

inline fun <reified VM : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> VM): VM =
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
            return requireNotNull(modelClass.cast(factory()))
        }
    }).get(VM::class.java)

// endregion

// region LiveData

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> = Transformations.map(this, body)

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> = Transformations.switchMap(this, body)

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}
// endregion
