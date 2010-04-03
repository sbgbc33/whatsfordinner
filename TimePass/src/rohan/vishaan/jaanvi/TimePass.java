/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rohan.vishaan.jaanvi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * A grid that displays a set of framed photos.
 * 
 */
public class TimePass extends Activity {

	private static int columnWidth;
	private static int columnHeight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.grid_2);

		GridView g = (GridView) findViewById(R.id.myGrid);
		g.setBackgroundColor(Color.GRAY);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;

		columnWidth = screenWidth / 3;
		columnHeight = screenHeight / 3;

		g.setNumColumns(2);

		g.setColumnWidth(columnWidth);
		g.setAdapter(new ImageAdapter(this));
	}

	public class MyImageView extends ImageView {

		public MyImageView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}

		public MyImageView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public MyImageView(Context context) {
			super(context);
		}

		private int currentImageId;
		private int origImageId;

		public void setOrigImageId(int i) {
			this.origImageId = i;
		}

		public int getCurrentImageId() {
			return currentImageId;
		}

		public int getOrigImageId() {
			return origImageId;
		}

		private boolean matched;

		public void setMatched() {
			this.matched = true;
			this.openCard();
		}

		public void openCard() {
			super.setImageResource(this.getOrigImageId());
		}

		public void closeCard() {
			super.setImageResource(R.drawable.sample_thumb_5);
		}
	}

	public class ImageAdapter extends BaseAdapter implements OnClickListener {
		private static final int DIALOG_YES_NO_MESSAGE = 1;
		private int clickCount;

		private int matchCount;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			MyImageView imageView;
			if (convertView == null) {
				imageView = new MyImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(
						columnWidth, columnHeight));
				imageView.setAdjustViewBounds(false);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(0, 5, 0, 5);
			} else {
				imageView = (MyImageView) convertView;
			}

			imageView.setImageResource(R.drawable.sample_thumb_5);
			imageView.setOrigImageId(mThumbIdsMatch[position]);

			imageView.setOnClickListener(this);

			return imageView;
		}

		public MyImageView lastClickOnImage;

		public void onClick(View v) {
			if (v instanceof MyImageView) {
				final MyImageView iv = (MyImageView) v;
				if (iv.matched) {
					return;// Ignore clicks on matched cards.
				}
				clickCount++;
				if (clickCount % 2 == 1) {
					// First Click
					lastClickOnImage = iv;
					iv.openCard();
				} else {
					// Second Click
					if (iv.getOrigImageId() == lastClickOnImage
							.getOrigImageId()) {
						// Match found.
						iv.setMatched();
						lastClickOnImage.setMatched();
						matchCount++;
						if (matchCount == 2) {
							showDialog(DIALOG_YES_NO_MESSAGE);
						}
					} else {
						iv.openCard();
						iv.postDelayed(new Runnable() {

							public void run() {
								lastClickOnImage.closeCard();
								iv.closeCard();
							}

						}, 1000);
					}
				}
			}
		}

		private final Context mContext;

		private final Integer[] mThumbIds = { R.drawable.sample_thumb_5,
				R.drawable.sample_thumb_5, R.drawable.sample_thumb_5,
				R.drawable.sample_thumb_5 };

		private final Integer[] mThumbIdsMatch = { R.drawable.sample_thumb_0,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_0,
				R.drawable.sample_thumb_1 };

		private final Integer hiddenCardId = R.drawable.sample_thumb_5;
		// , R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.sample_thumb_5, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, };
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_YES_NO_MESSAGE:
			return new AlertDialog.Builder(this).setIcon(
					R.drawable.alert_dialog_icon).setTitle(
					R.string.alert_dialog_two_buttons_title).setPositiveButton(
					R.string.alert_dialog_ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							/* User clicked OK so do some stuff */
						}
					}).setNegativeButton(R.string.alert_dialog_cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							/* User clicked Cancel so do some stuff */
						}
					}).create();
		}
		return null;
	}

	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private static final int DIALOG_YES_NO_LONG_MESSAGE = 2;
	private static final int DIALOG_LIST = 3;
	private static final int DIALOG_PROGRESS = 4;
	private static final int DIALOG_SINGLE_CHOICE = 5;
	private static final int DIALOG_MULTIPLE_CHOICE = 6;
	private static final int DIALOG_TEXT_ENTRY = 7;

	private static final int MAX_PROGRESS = 100;
	private ProgressDialog mProgressDialog;
	private int mProgress;
	private Handler mProgressHandler;

}
