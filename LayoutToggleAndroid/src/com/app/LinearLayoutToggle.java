/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 * @author prxgomes
 */
public class LinearLayoutToggle extends LinearLayout {

    // sinaliza se o layout esta visivel ou nao
    private boolean _visible;
    private Context context;
    private TextView text;
    private Animation fadeIn;
    private Animation fadeOut;
    private int typeToggle = 0;
    public static final int TO_LEFT_RIGHT = 0;
    public static final int TO_UP_DOWN = 1;

    public LinearLayoutToggle(Context context) {
        // Construtor padrao
        super(context);
        // inicializa o componentes da classe
        this.initClass(context);
    }

    public LinearLayoutToggle(Context context, AttributeSet attrs) {
        // Construtor padrao
        super(context, attrs);
        // inicializa o componentes da classe
        this.initClass(context);
    }

    /**
     * Inicializa os componentes e eventos da classe
     * @param context 
     */
    private void initClass(Context context) {
        // refencia do context
        this.context = context;
        // Orientacao default
        this.setOrientation(LinearLayout.VERTICAL);
        // cria um text view para exibição do titulo do layout
        this.text = new TextView(context);
        this.addView(text);
        // Cria o evento de click ao titulo
        this.addTouchEvent();
        // Cria a animação para mostrar ou esconder o bloco
        this.initFade();
        // Inicia como padrao mostrando o layout
        this.show();
    }

    /**
     * Altera o texto do layout
     * @param text 
     */
    public void setTextLabel(String text) {
        this.text.setText(text);
    }

    /**
     * Altera o text view do layout incluindo o evento 'toggle' no mesmo
     * @param text 
     */
    public void setText(TextView text) {
        this.text = text;
        this.addTouchEvent();
    }

    /**
     * Altera a animação de mostrar / esconder o layout
     * Animação horizontal (typeToggle == TO_LEFT_RIGHT)
     * Animação vertical (typeToggle == TO_UP_DOWN)
     * @param typeToggle inteiro 0 ou 1
     */
    public void setTypeToggle(int typeToggle) {
        this.typeToggle = typeToggle;
        this.initFade();
    }
    
    /**
     * Cria o Listener que chama a funcao toggle e adiciona no text view
     */
    private void addTouchEvent() {
        OnClickListener click = new OnClickListener() {
            public void onClick(View view) {
                toggle();
            }
        };
        this.text.setOnClickListener(click);
    }

    /**
     * Cria as  animações e de acordo com o typeToggle
     */
    private void initFade() {
        if (typeToggle == 0) {
            this.fadeIn = AnimationUtils.makeInAnimation(this.context, true);
            this.fadeOut = AnimationUtils.makeOutAnimation(this.context, true);
        } else {
            this.fadeIn = AnimUtils.setLayoutAnim_slidedownfromtop(context);
            this.fadeOut = AnimUtils.setLayoutAnim_slideupfrombottom(context);
        }
    }

    public void show() {
        this._visible = true;
        for (int i = this.getChildCount(); i > 0; i--) {
            if (this.getChildAt(i) != null) {
                this.getChildAt(i).startAnimation(fadeIn);
                this.getChildAt(i).setVisibility(View.VISIBLE);
            }
        }
    }

    public void hide() {
        this._visible = false;
        for (int i = this.getChildCount(); i > 0; i--) {
            if (this.getChildAt(i) != null) {
                this.getChildAt(i).startAnimation(fadeOut);
                this.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    public void toggle() {
        this._visible = !_visible;
        if (_visible) {
            this.show();
        } else {
            this.hide();
        }
    }
}
