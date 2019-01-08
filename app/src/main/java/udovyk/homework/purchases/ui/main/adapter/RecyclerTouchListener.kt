package udovyk.homework.purchases.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import javax.inject.Inject
import javax.inject.Singleton

class RecyclerTouchListener @Inject constructor(context: Context) :
    GestureDetector.SimpleOnGestureListener(), RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector = GestureDetector(context, this)

    var listener: ClickListener? = null

    var rv: RecyclerView? = null

    override fun onSingleTapUp(e: MotionEvent?): Boolean = true

    override fun onLongPress(e: MotionEvent?) {
        val child = rv?.findChildViewUnder(e!!.x, e.y)
        if (child != null && listener != null && rv != null) {
            listener!!.onLongPress(rv!!.getChildAdapterPosition(child), child);
        }
    }

    override fun onTouchEvent(p0: RecyclerView, p1: MotionEvent) {

    }

    override fun onInterceptTouchEvent(p0: RecyclerView, p1: MotionEvent): Boolean {
        val child = p0?.findChildViewUnder(p1!!.x, p1.y)
        if (child != null && listener != null && gestureDetector.onTouchEvent(p1) ) {
            listener!!.onPress(p0.getChildLayoutPosition(child), child)
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

    interface ClickListener {
        fun onPress(position: Int, view: View)
        fun onLongPress(position: Int, view: View)
    }
}