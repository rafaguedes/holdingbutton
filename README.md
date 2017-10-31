# Holding Button
Holding button component to be used instead a confirm dialog with callback when this is finished!

I'm in process to publish this library with Maven Repository.<br />
When it's not finished, i'm making this available like local library.

## Sample Example
<br />

![Holding button usage](https://media.giphy.com/media/l1J9FZt9z14GsNgyc/giphy.gif)


Use this library when you need to create a confirm button with time.<br />
You can configure how much time you need to confirm the action.

## Basic Configuration
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

## Basic Usage
```
HoldingButton holdingButton = findViewById(R.id.holdingButton);
holdingButton.setOnFinishEventListener(() -> {
    Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_LONG).show();
});
```
