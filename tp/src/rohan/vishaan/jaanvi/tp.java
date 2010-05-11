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
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
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
public class tp extends Activity {

	private static final String TAG = "tp";

	private static int singleImageColumnWidth;
	private static int singleImageColumnHeight;

	private Config getConfig() {
		return new Config(4);
	}

	private Config config;

	private int numberOfColumns;

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

		Log.v(TAG, "screenWidth = " + screenWidth);
		Log.v(TAG, "screenHeight = " + screenHeight);

		this.config = getConfig();
		int heightUsedByMenubarAndTitleBar = 80;
		screenHeight -= heightUsedByMenubarAndTitleBar;

		int verticalSpacer = 1 * this.config.getHowManyImages();
		screenHeight -= verticalSpacer;
		// numberOfColumns = 5;

		g.setBackgroundColor(Color.WHITE);
		screenWidth -= numberOfColumns * 5;

		// singleImageColumnWidth = screenWidth / numberOfColumns;

		int totalNumberOfImagesToShow = this.config.getHowManyImages() * 2;

		// int numberOfRows = totalNumberOfImagesToShow / numberOfColumns;

		// singleImageColumnHeight = screenHeight / numberOfRows;

		Box optimizedSizes = GridUtil.getBoxesPerRow(totalNumberOfImagesToShow,
				screenWidth, screenHeight);

		singleImageColumnWidth = optimizedSizes.getW();
		singleImageColumnHeight = optimizedSizes.getH();
		numberOfColumns = optimizedSizes.getNumberPerRow();

		Log.v(TAG, "singleImageColumnWidth = " + singleImageColumnWidth
				+ " with " + this.numberOfColumns);
		Log.v(TAG, "singleImageColumnHeight = " + singleImageColumnHeight
				+ " with " + this.numberOfColumns);
		Log.v(TAG, "w/h ration = "
				+ (singleImageColumnWidth * 1.0 / singleImageColumnHeight));

		g.setNumColumns(numberOfColumns);

		g.setColumnWidth(singleImageColumnWidth);
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
			super.setImageResource(R.drawable.facecard);
		}
	}

	public class ImageAdapter extends BaseAdapter implements OnClickListener {
		private static final int DIALOG_YES_NO_MESSAGE = 1;
		private int clickCount;

		private int matchCount;

		public ImageAdapter(tp c) {
			mContext = c;
		}

		public int getCount() {
			return mContext.config.getHowManyImages() * 2;
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
						singleImageColumnWidth, singleImageColumnHeight));
				imageView.setAdjustViewBounds(false);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(0, 5, 0, 5);
			} else {
				imageView = (MyImageView) convertView;
			}

			imageView.setImageResource(R.drawable.facecard);
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
						if (matchCount == config.getHowManyImages()) {
							showDialog(DIALOG_YES_NO_MESSAGE);
							restart();
						}
					} else {
						iv.openCard();
						iv.postDelayed(new Runnable() {

							public void run() {
								lastClickOnImage.closeCard();
								iv.closeCard();
							}

						}, 2100);
					}
				}
			}
		}

		private void restart() {
			Intent intent = mContext.getIntent();
			startActivity(intent);
			// finish();
		}

		private final tp mContext;

		private final Integer[] mThumbIds = { R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard,
				R.drawable.facecard, R.drawable.facecard, R.drawable.facecard };

		private final Integer[] mThumbIdsMatch = { R.drawable.jaanvi,
				R.drawable.vishaan, R.drawable.jaanvi, R.drawable.vishaan,
				R.drawable.sample_thumb_4, R.drawable.sample_thumb_4,
				R.drawable.sample_thumb_7, R.drawable.sample_thumb_7,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_1,
				R.drawable.sample_thumb_1 };

		private final Integer hiddenCardId = R.drawable.facecard;
		// , R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
		// R.drawable.sample_thumb_7, R.drawable.sample_thumb_0,
		// R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
		// R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
		// R.drawable.facecard, R.drawable.sample_thumb_6,
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
