// Generated by view binder compiler. Do not edit!
package com.example.favouritescreen.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import com.example.favouritescreen.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemFavouriteRecyclerBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final AppCompatImageView deleteImageView;

  @NonNull
  public final TextView descriptionWordTextView;

  @NonNull
  public final TextView transcriptionTextView;

  @NonNull
  public final TextView wordTextView;

  private ItemFavouriteRecyclerBinding(@NonNull MaterialCardView rootView,
      @NonNull AppCompatImageView deleteImageView, @NonNull TextView descriptionWordTextView,
      @NonNull TextView transcriptionTextView, @NonNull TextView wordTextView) {
    this.rootView = rootView;
    this.deleteImageView = deleteImageView;
    this.descriptionWordTextView = descriptionWordTextView;
    this.transcriptionTextView = transcriptionTextView;
    this.wordTextView = wordTextView;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemFavouriteRecyclerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemFavouriteRecyclerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_favourite_recycler, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemFavouriteRecyclerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deleteImageView;
      AppCompatImageView deleteImageView = rootView.findViewById(id);
      if (deleteImageView == null) {
        break missingId;
      }

      id = R.id.descriptionWordTextView;
      TextView descriptionWordTextView = rootView.findViewById(id);
      if (descriptionWordTextView == null) {
        break missingId;
      }

      id = R.id.transcriptionTextView;
      TextView transcriptionTextView = rootView.findViewById(id);
      if (transcriptionTextView == null) {
        break missingId;
      }

      id = R.id.wordTextView;
      TextView wordTextView = rootView.findViewById(id);
      if (wordTextView == null) {
        break missingId;
      }

      return new ItemFavouriteRecyclerBinding((MaterialCardView) rootView, deleteImageView,
          descriptionWordTextView, transcriptionTextView, wordTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
