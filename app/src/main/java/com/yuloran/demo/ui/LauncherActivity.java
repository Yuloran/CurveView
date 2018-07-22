package com.yuloran.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuloran.demo.R;
import com.yuloran.demo.ui.curveview.CurveViewActivity;
import com.yuloran.demo.ui.rxjava2.RxJava2Activity;

/**
 * Author & Date: Yuloran, 2018/7/22 8:14
 * Function:
 */
public class LauncherActivity extends Activity {

    private RecyclerView mRvFunctionWizard;

    private String[] mFunctions;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

        mFunctions = getResources().getStringArray(R.array.functions);

        initView();
    }

    private void initView() {
        mRvFunctionWizard = (RecyclerView) findViewById(R.id.rv_function_wizard);
        mRvFunctionWizard.setLayoutManager(new LinearLayoutManager(mRvFunctionWizard.getContext()));
        mRvFunctionWizard.addItemDecoration(new DividerItemDecoration(mRvFunctionWizard
                .getContext(), LinearLayoutManager.VERTICAL));
        mRvFunctionWizard.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                                              .inflate(android.R.layout.simple_list_item_1, parent, false);
                return new FunctionWizardViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof FunctionWizardViewHolder) {
                    ((FunctionWizardViewHolder) holder).tv.setText(mFunctions[position]);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = holder.getAdapterPosition();
                            switch (position) {
                                case 0:
                                    startActivity(new Intent(LauncherActivity.this, RxJava2Activity.class));
                                    break;
                                case 1:
                                    startActivity(new Intent(LauncherActivity.this, CurveViewActivity.class));
                                    break;
                                default:
                            }
                        }
                    });
                }
            }

            @Override
            public int getItemCount() {
                return mFunctions.length;
            }

            class FunctionWizardViewHolder extends RecyclerView.ViewHolder {

                private TextView tv;

                FunctionWizardViewHolder(View itemView) {
                    super(itemView);
                    tv = (TextView) itemView.findViewById(android.R.id.text1);
                }
            }

        });
    }
}
