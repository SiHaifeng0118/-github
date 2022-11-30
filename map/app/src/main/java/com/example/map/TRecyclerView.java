package com.example.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class TRecyclerView extends RecyclerView {

    private LinearLayout itemRoot; //item的根布局
    private LinearLayout itemRootLast;//上一次滑动的Item根布局
    private int mlastX = 0;//上次X轴的滑动坐标
    private int mlastY = 0;//上次Y轴的滑动坐标
    private final int MAX_WIDTH = 220;//滑动的最大距离
    private Context mContext;
    private Scroller mScroller;//滑动辅助类

    public TRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public TRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;
        mScroller = new Scroller(context,new LinearInterpolator(context,null));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        int maxLength = dipToPx(mContext, MAX_WIDTH);
        int x = (int) e.getX();
        int y = (int) e.getY();
        final int  position;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //恢复上一次侧滑的ITEM
                if(itemRootLast != null){
                    itemRootLast.scrollTo(0, 0);
                    invalidate();//请求View树进行重绘
                }
                //根据点击的坐标获取那个Item被点击了
                View view  = findChildViewUnder(x, y);
                if(view == null){
                    return false;
                }
                final TAdapter.ViewHolder viewHolder = (TAdapter.ViewHolder) getChildViewHolder(view);
                itemRootLast = itemRoot = (LinearLayout) viewHolder.textView.getParent().getParent();
                position= viewHolder.getAdapterPosition();
                viewHolder.button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         mOnItemClickListener.onClick(viewHolder.itemView,position);

                        }
                    });

            }
            break;
            case MotionEvent.ACTION_MOVE: {
                if(itemRoot == null){
                    return  false;
                }
                //Math.abs返回一个数的绝对值
                if(  Math.abs(mlastX -x)>0 && Math.abs(mlastX -x) > Math.abs(mlastY-y)){

                    //返回当前滑动View左边界的位置,(相对于未移动之前,此刻移动了多少)
                    int scrollX = itemRoot.getScrollX();
                    int newScrollX = scrollX + mlastX - x;
                    if (newScrollX < 0) {
                        newScrollX = 0;
                    } else if (newScrollX > maxLength) {
                        newScrollX = maxLength;
                    }
                    itemRoot.scrollTo(newScrollX, 0);
                }
            }
            break;

        }
        mlastX = x;
        mlastY = y;

        return super.onTouchEvent(e);
    }


    //计算ViewGroup如何滑动,computeScroll是通过draw调用
    //scroll类计算其怎么滚动
    //computescroll控制其什么时候滚动
    //mScroller.computeScrollOffset()滚动过程是否完成,如果没有完成，
    // 就需要不停的scrollTo下去，所以在最后需要加一个invalidate(),这样可以再次触发computScroll,直到滚动已经结束。
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            itemRoot.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            if(itemRootLast !=null){
                itemRootLast.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            }
        }
        invalidate();
    }
    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }


    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onClick(View view, int position);
    }
    public  void setOnItemClickListenre(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
//            case MotionEvent.ACTION_UP: {
//                if(itemRoot ==null){
//                    return  false;
//                }
//                int scrollX = itemRoot.getScrollX();
//                int newScrollX = scrollX + mlastX - x;
//                if (scrollX > maxLength / 2) {
//                    newScrollX = maxLength;
//                } else {
//                    newScrollX = 0;
//                }
//                mScroller.startScroll(scrollX, 0, newScrollX - scrollX, 0);
//                invalidate();
//                //重新draw()
//            }
//            break;


