# Holding Button
Holding button component to be used instead a confirm dialog

## Sample Example
<br />

![Holding button usage](https://media.giphy.com/media/l1J9FZt9z14GsNgyc/giphy.gif)


Use this library when you need to create a confirm button with time.<br />
You can configure how much time you need to confirm the action.

Configuration
```
<br.com.yourapp.holdingbutton.HoldingButton
        android:id="@+id/holdingButton"
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:layout_marginTop="20dp"
        android:text="Go forward?"
        android:textColor="#FFFFFF"
        android:textSize="5sp"
        app:backColor="#F00"
        app:overColor="#00F"
        app:timer="3000" />
```