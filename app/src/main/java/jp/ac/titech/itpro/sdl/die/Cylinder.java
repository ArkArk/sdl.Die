package jp.ac.titech.itpro.sdl.die;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cylinder implements Obj {

    private final float radius;
    private final float height;
    private final int segments;

    private float[] topVertices;
    private float[] bottomVertices;
    private float[] sideVertices;

    private FloatBuffer topVbuf;
    private FloatBuffer bottomVbuf;
    private FloatBuffer sideVbuf;

    Cylinder(float radius, float height, int segments) {
        this.radius = radius;
        this.height = height;
        this.segments = segments;
        init();
    }

    private void init() {
        initTop();
        initBottom();
        initSide();
    }

    private void initTop() {
        topVertices = new float[segments*3*3];
        for(int i=0; i<segments; i++) {
            double rad1 = Math.PI * 2 * i / segments;
            double rad2 = Math.PI * 2 * (i+1) / segments;
            topVertices[i*3*3 + 0*3 + 0] = 0;
            topVertices[i*3*3 + 0*3 + 1] = 0;
            topVertices[i*3*3 + 0*3 + 2] = +height/2;
            topVertices[i*3*3 + 1*3 + 0] = (float)(Math.cos(rad1) * radius);
            topVertices[i*3*3 + 1*3 + 1] = (float)(Math.sin(rad1) * radius);
            topVertices[i*3*3 + 1*3 + 2] = +height/2;
            topVertices[i*3*3 + 2*3 + 0] = (float)(Math.cos(rad2) * radius);
            topVertices[i*3*3 + 2*3 + 1] = (float)(Math.sin(rad2) * radius);
            topVertices[i*3*3 + 2*3 + 2] = +height/2;
        }
        topVbuf = ByteBuffer
                .allocateDirect(topVertices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        topVbuf.put(topVertices);
        topVbuf.position(0);
    }

    private void initBottom() {
        bottomVertices = new float[segments*3*3];
        for(int i=0; i<segments; i++) {
            double rad1 = Math.PI * 2 * i / segments;
            double rad2 = Math.PI * 2 * (i+1) / segments;
            bottomVertices[i*3*3 + 0*3 + 0] = 0;
            bottomVertices[i*3*3 + 0*3 + 1] = 0;
            bottomVertices[i*3*3 + 0*3 + 2] = -height/2;
            bottomVertices[i*3*3 + 1*3 + 0] = (float)(Math.cos(rad1) * radius);
            bottomVertices[i*3*3 + 1*3 + 1] = (float)(Math.sin(rad1) * radius);
            bottomVertices[i*3*3 + 1*3 + 2] = -height/2;
            bottomVertices[i*3*3 + 2*3 + 0] = (float)(Math.cos(rad2) * radius);
            bottomVertices[i*3*3 + 2*3 + 1] = (float)(Math.sin(rad2) * radius);
            bottomVertices[i*3*3 + 2*3 + 2] = -height/2;
        }
        bottomVbuf = ByteBuffer
                .allocateDirect(bottomVertices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        bottomVbuf.put(bottomVertices);
        bottomVbuf.position(0);
    }

    private void initSide() {
        sideVertices = new float[segments*2*3*3];
        for(int i=0; i<segments; i++) {
            double rad1 = Math.PI * 2 * i / segments;
            double rad2 = Math.PI * 2 * (i+1) / segments;
            sideVertices[i*2*3*3 + 0*3*3 + 0*3 + 0] = (float)(Math.cos(rad1) * radius);
            sideVertices[i*2*3*3 + 0*3*3 + 0*3 + 1] = (float)(Math.sin(rad1) * radius);;
            sideVertices[i*2*3*3 + 0*3*3 + 0*3 + 2] = +height/2;
            sideVertices[i*2*3*3 + 0*3*3 + 1*3 + 0] = (float)(Math.cos(rad1) * radius);
            sideVertices[i*2*3*3 + 0*3*3 + 1*3 + 1] = (float)(Math.sin(rad1) * radius);
            sideVertices[i*2*3*3 + 0*3*3 + 1*3 + 2] = -height/2;
            sideVertices[i*2*3*3 + 0*3*3 + 2*3 + 0] = (float)(Math.cos(rad2) * radius);
            sideVertices[i*2*3*3 + 0*3*3 + 2*3 + 1] = (float)(Math.sin(rad2) * radius);
            sideVertices[i*2*3*3 + 0*3*3 + 2*3 + 2] = +height/2;
            sideVertices[i*2*3*3 + 1*3*3 + 0*3 + 0] = (float)(Math.cos(rad2) * radius);
            sideVertices[i*2*3*3 + 1*3*3 + 0*3 + 1] = (float)(Math.sin(rad2) * radius);;
            sideVertices[i*2*3*3 + 1*3*3 + 0*3 + 2] = -height/2;
            sideVertices[i*2*3*3 + 1*3*3 + 1*3 + 0] = (float)(Math.cos(rad2) * radius);
            sideVertices[i*2*3*3 + 1*3*3 + 1*3 + 1] = (float)(Math.sin(rad2) * radius);
            sideVertices[i*2*3*3 + 1*3*3 + 1*3 + 2] = +height/2;
            sideVertices[i*2*3*3 + 1*3*3 + 2*3 + 0] = (float)(Math.cos(rad1) * radius);
            sideVertices[i*2*3*3 + 1*3*3 + 2*3 + 1] = (float)(Math.sin(rad1) * radius);
            sideVertices[i*2*3*3 + 1*3*3 + 2*3 + 2] = -height/2;
        }
        sideVbuf = ByteBuffer
                .allocateDirect(sideVertices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        sideVbuf.put(sideVertices);
        sideVbuf.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        drawTop(gl);
        drawBottom(gl);
        drawSide(gl);
    }

    private void drawTop(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, topVbuf);
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, segments*3);
    }

    private void drawBottom(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bottomVbuf);
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, segments*3);
    }

    private void drawSide(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, sideVbuf);
        for(int i=0; i<segments; i++) {
            double rad = Math.PI * 2 * (i + 0.5) / segments;
            float nx = (float)(Math.cos(rad));
            float ny = (float)(Math.sin(rad));
            float nz = 0;
            gl.glNormal3f(nx, ny, nz);
            gl.glDrawArrays(GL10.GL_TRIANGLES, i*2*3, 2*3);
        }
    }
}
