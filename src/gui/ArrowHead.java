package gui;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Path2D;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class ArrowHead{

	int x1,x2,x3,x4,x5,x6,y1,y2,y3,y4,y5,y6;
	Polygon triangle ;


	public ArrowHead(edge_data e, graph g) {


		int src = e.getSrc();
		int dest = e.getDest();


		Point3D pSrc = g.getNode(src).getLocation();
		Point3D pDest = g.getNode(dest).getLocation();
		
		double v = Math.sqrt(Math.pow((x2-x1), 2)+ Math.pow((y2-y1), 2));
		double vFraction = v/5;
		double vFrac = v/15;

		x1 = pSrc.ix();
		y1 = pSrc.iy();

		x2 = pDest.ix();
		y2 = pDest.iy();

		double slope = (double)(y2-y1)/(x2-x1);

		x3 = (int)(x2 +((-slope)*vFraction));
		y3 = (int)(y2 +((-slope)*vFraction));

		x4 = (int)(x2 +((-slope)*vFrac));
		y4 = (int)(y2 +((-slope)*vFrac));

		x5 = (int)(x4 +((1/(-slope))*5));
		y5 = (int)(y4 +((1/(-slope))*5));

		x6 = (int)(x4 +((1/(slope))*5));
		y6 = (int)(y4 +((1/(slope))*5));

		int xpoints[] = {x3,x5,x6};
		int ypoints[] = {y3,y5,y6};

		int npoints = 3;

		triangle = new Polygon(xpoints, ypoints, npoints);





	}

}
