package br.com.yourapp.holdingbutton;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * HoldingButton Class created to handle with no confirm dialog.
 * The users need to keep pressed the button to confirm the action.
 *
 * Created by Rafael Guedes Alves
 * rafael.alves@ufabc.edu.br
 *
 * 10/25/2017
 */
public class HoldingButton extends RelativeLayout {

    LayoutInflater mInflater;
    TextView textView;

    LinearLayout over;
    LinearLayout back;

    ObjectAnimator anim;
    boolean isCancelled = false;

    private OnFinishEventListener listener;

    int timer = 3000;

    /**
     * Constant to get the layout width
     */
    public static final Property<View, Integer> PROPERTY_WIDTH =
        new Property<View, Integer>(Integer.class, "viewLayoutWidth") {

            @Override
            public void set(View object, Integer value) {
                object.getLayoutParams().width = value.intValue();
                object.requestLayout();
            }

            @Override
            public Integer get(View object) {
                return object.getLayoutParams().width;
            }
    };

    /**
     * Touch listener to start/end the loading/confirm event
     */
    OnTouchListener onTouchListener = new OnTouchListener()  {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                over.setVisibility(View.VISIBLE);

                anim = ObjectAnimator.ofInt(over, PROPERTY_WIDTH, 0, back.getMeasuredWidth());
                anim.setDuration(timer);
                anim.start();

                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {}

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(!isCancelled) {
                            listener.onFinish();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        isCancelled = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                });

                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                anim.cancel();
                over.setVisibility(View.GONE);
                isCancelled = false;
                return true;
            }
            return false;
        }
    };

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public HoldingButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public HoldingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context
     */
    public HoldingButton(Context context) {
        super(context);
        initialize(context, null);
    }

    /**
     * Initialize the component with attributeset from XML
     *
     * @param context
     * @param attrs
     */
    private void initialize(Context context, AttributeSet attrs) {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.holding_button, this, true);
        over = view.findViewById(R.id.over);
        back = view.findViewById(R.id.back);
        textView = view.findViewById(R.id.textView);

        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HoldingButton);
            textView.setText(typedArray.getString(R.styleable.HoldingButton_android_text));
            textView.setTextColor(typedArray.getColor(R.styleable.HoldingButton_android_textColor, Color.BLACK));
            textView.setTextSize(typedArray.getDimension(R.styleable.HoldingButton_android_textSize, 12.0f));

            GradientDrawable gradientDrawableBack = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {typedArray.getColor(R.styleable.HoldingButton_backColor, Color.BLACK),typedArray.getColor(R.styleable.HoldingButton_backColor, Color.BLACK)});
            gradientDrawableBack.setCornerRadius(20f);

            GradientDrawable gradientDrawableOver = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {typedArray.getColor(R.styleable.HoldingButton_overColor, Color.BLACK),typedArray.getColor(R.styleable.HoldingButton_overColor, Color.BLACK)});
            gradientDrawableOver.setCornerRadius(20f);

            if(Build.VERSION.SDK_INT >= 16) {
                back.setBackground(gradientDrawableBack);
                over.setBackground(gradientDrawableOver);
            }
            else {
                back.setBackgroundDrawable(gradientDrawableBack);
                over.setBackgroundDrawable(gradientDrawableOver);
            }

            timer = typedArray.getInt(R.styleable.HoldingButton_timer, 3000);

            typedArray.recycle();
        }

        back.setOnTouchListener(onTouchListener);
    }

    /**
     * Listener to handle the finish event
     *
     * @param eventListener
     */
    public void setOnFinishEventListener(OnFinishEventListener eventListener) {
        listener = eventListener;
    }

}
