package com.example.shopar;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopar.databinding.FragmentARfragmentBinding;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;

public class AugmentationFragment extends Fragment {

    private String modelUri;
    private ArFragment aRfragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_a_rfragment,container,false);
        modelUri = getArguments().getString("modeluri");
        aRfragment =  (ArFragment) getChildFragmentManager().findFragmentById(R.id.arFragment);
        aRfragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                placeModel(hitResult.createAnchor());
            }
        });
        return root;
    }

    private void placeModel(Anchor anchor) {
        ModelRenderable
                .builder()
                .setSource(getContext(),
                        RenderableSource
                         .builder()
                        .setSource(getContext(), Uri.parse(modelUri), RenderableSource.SourceType.GLB).setRecenterMode(RenderableSource.RecenterMode.CENTER)
                        .build()
                        ).setRegistryId(modelUri).build().thenAccept(modelRenderable -> addNodeToscene(modelRenderable,anchor))
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                    builder.setMessage(throwable.getMessage()).show();
                    return null;
                });
    }

    private void addNodeToscene(ModelRenderable modelRenderable, Anchor anchor) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setRenderable(modelRenderable);
        aRfragment.getArSceneView().getScene().addChild(anchorNode);
    }
}