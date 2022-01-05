package com.example.shopar;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.ar.core.AugmentedFace;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.AugmentedFaceNode;

import java.util.Collection;
import java.util.HashMap;

public class faceaugmentation extends Fragment {

    private CustomArFragment customArFragment;
    private boolean isAdded = false;
    private String modelUri;
    private ModelRenderable modelRenderable;
    private final HashMap<AugmentedFace, AugmentedFaceNode> facesNodes = new HashMap<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faceaugmentation, container, false);
        customArFragment = (CustomArFragment) getChildFragmentManager().findFragmentById(R.id.facearfragment);
        modelUri = getArguments().getString("modeluri");
        ModelRenderable
                .builder()
                .setSource(getContext(),
                        RenderableSource
                                .builder()
                                .setSource(getContext(), Uri.parse(modelUri), RenderableSource.SourceType.GLB).setRecenterMode(RenderableSource.RecenterMode.NONE)
                                .build()
                ).setRegistryId(modelUri).build().thenAccept(modelRenderable1 -> {
            modelRenderable = modelRenderable1;
            modelRenderable.setShadowCaster(false);
            modelRenderable.setShadowReceiver(false);
        });
        customArFragment.getArSceneView().setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST);
        customArFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            if(modelRenderable ==null){
                return;
            }
            Frame frame = customArFragment.getArSceneView().getArFrame();
            Collection<AugmentedFace> augmentedFaces = frame.getUpdatedTrackables(AugmentedFace.class);
            for(AugmentedFace augmentedFace : augmentedFaces){
                if(isAdded)
                    return;
                AugmentedFaceNode augmentedFaceNode = new AugmentedFaceNode(augmentedFace);
                augmentedFaceNode.setFaceRegionsRenderable(modelRenderable);
                augmentedFaceNode.setParent(customArFragment.getArSceneView().getScene());
                facesNodes.put(augmentedFace,augmentedFaceNode);
                isAdded = true;
            }
        });
        return view;
    }
}