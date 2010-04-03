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
import android.os.Bundle;
import android.os.Handler;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.grid_2);

		GridView g = (GridView) findViewById(R.id.myGrid);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;

		int columnWidth = screenWidth / 2;
		int columnHeight = screenHeight / 2;

		g.setColumnWidth(columnWidth);
		g.setAdapter(new ImageAdapter(this));
	}

	public class MyImageView extends ImageView {

		public MyImageView(Context context) {
			super(context);
		}

		private int imageId;

		public int getImageId() {
			return imageId;
		}

		@Override
		public void setImageResource(int resId) {
			this.imageId = resId;
		}

	}

	public class ImageAdapter extends BaseAdapter implements OnClickListener {
		private static final int DIALOG_YES_NO_MESSAGE = 1;

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
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(45, 45));
				imageView.setAdjustViewBounds(false);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(0, 0, 0, 0);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(mThumbIds[position]);

			imageView.setOnClickListener(this);

			return imageView;
		}

		public void onClick(View v) {
			if (v instanceof ImageView) {
				ImageView iv = (ImageView) v;
				iv.setImageResource(mThumbIds[0]);
				iv.refreshDrawableState();
			}
			// showDialog(DIALOG_YES_NO_MESSAGE);
		}

		private final Context mContext;

		private final Integer[] mThumbIds = { R.drawable.sample_thumb_0,
				R.drawable.sample_thumb_1, R.drawable.sample_thumb_2,
				R.drawable.sample_thumb_3 };
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
