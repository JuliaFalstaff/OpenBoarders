// Generated by view binder compiler. Do not edit!
package com.example.historyscreen.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.historyscreen.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemHistoryRecyclerViewBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final TextView descriptionHistoryTextViewRecyclerItem;

  @NonNull
  public final TextView headerHistoryTextViewRecyclerItem;

  @NonNull
  public final TextView transcriptionHistoryTextView;

  private ItemHistoryRecyclerViewBinding(@NonNull MaterialCardView rootView,
      @NonNull TextView descriptionHistoryTextViewRecyclerItem,
      @NonNull TextView headerHistoryTextViewRecyclerItem,
      @NonNull TextView transcriptionHistoryTextView) {
    this.rootView = rootView;
    this.descriptionHistoryTextViewRecyclerItem = descriptionHistoryTextViewRecyclerItem;
    this.headerHistoryTextViewRecyclerItem = headerHistoryTextViewRecyclerItem;
    this.transcriptionHistoryTextView = transcriptionHistoryTextView;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemHistoryRecyclerViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemHistoryRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_history_recycler_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemHistoryRecyclerViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.descriptionHistoryTextViewRecyclerItem;
      TextView descriptionHistoryTextViewRecyclerItem = rootView.findViewById(id);
      if (descriptionHistoryTextViewRecyclerItem == null) {
        break missingId;
      }

      id = R.id.headerHistoryTextViewRecyclerItem;
      TextView headerHistoryTextViewRecyclerItem = rootView.findViewById(id);
      if (headerHistoryTextViewRecyclerItem == null) {
        break missingId;
      }

      id = R.id.transcriptionHistoryTextView;
      TextView transcriptionHistoryTextView = rootView.findViewById(id);
      if (transcriptionHistoryTextView == null) {
        break missingId;
      }

      return new ItemHistoryRecyclerViewBinding((MaterialCardView) rootView,
          descriptionHistoryTextViewRecyclerItem, headerHistoryTextViewRecyclerItem,
          transcriptionHistoryTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}