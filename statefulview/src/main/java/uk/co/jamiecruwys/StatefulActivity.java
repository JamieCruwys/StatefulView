package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.jamiecruwys.statefulview.R;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 07/04/2017.
 */
public class StatefulActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stateful_activity);
	}
}