/*
 * Copyright (C) 2014-2015 OpenKeeper
 *
 * OpenKeeper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenKeeper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenKeeper.  If not, see <http://www.gnu.org/licenses/>.
 */
package toniarts.openkeeper.view.map.construction.room;

import com.jme3.asset.AssetManager;
import com.jme3.scene.BatchNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.awt.Point;
import toniarts.openkeeper.utils.AssetUtils;
import toniarts.openkeeper.view.map.construction.DoubleQuadConstructor;
import static toniarts.openkeeper.world.MapLoader.TILE_WIDTH;

/**
 * Manages combat pit door placement, currently it is decoupled from the actual
 * door. But the rules are pretty static, so... And now one so visibly uses this
 * door.
 *
 * @author ArchDemon
 */
public class CombatPitConstructor extends DoubleQuadConstructor {

    public CombatPitConstructor(AssetManager assetManager, toniarts.openkeeper.common.RoomInstance roomInstance) {
        super(assetManager, roomInstance);
    }

    @Override
    protected BatchNode constructFloor() {
        BatchNode root = new BatchNode();
        String modelName = roomInstance.getRoom().getCompleteResource().getName();
        //Point start = roomInstance.getCoordinates().get(0);

        // Contruct the tiles
        boolean door = false;
        for (Point p : roomInstance.getCoordinates()) {

            // Figure out which peace by seeing the neighbours
            boolean N = roomInstance.hasCoordinate(new Point(p.x, p.y - 1));
            boolean NE = roomInstance.hasCoordinate(new Point(p.x + 1, p.y - 1));
            boolean E = roomInstance.hasCoordinate(new Point(p.x + 1, p.y));
            boolean SE = roomInstance.hasCoordinate(new Point(p.x + 1, p.y + 1));
            boolean S = roomInstance.hasCoordinate(new Point(p.x, p.y + 1));
            boolean SW = roomInstance.hasCoordinate(new Point(p.x - 1, p.y + 1));
            boolean W = roomInstance.hasCoordinate(new Point(p.x - 1, p.y));
            boolean NW = roomInstance.hasCoordinate(new Point(p.x - 1, p.y - 1));

            boolean northInside = isTileInside(roomInstance, new Point(p.x, p.y - 1));
            boolean northEastInside = isTileInside(roomInstance, new Point(p.x + 1, p.y - 1));
            boolean eastInside = isTileInside(roomInstance, new Point(p.x + 1, p.y));
            boolean southEastInside = isTileInside(roomInstance, new Point(p.x + 1, p.y + 1));
            boolean southInside = isTileInside(roomInstance, new Point(p.x, p.y + 1));
            boolean southWestInside = isTileInside(roomInstance, new Point(p.x - 1, p.y + 1));
            boolean westInside = isTileInside(roomInstance, new Point(p.x - 1, p.y));
            boolean northWestInside = isTileInside(roomInstance, new Point(p.x - 1, p.y - 1));

            if (!door && southInside) {

                // This is true, the door is always like this, it might not look correct visually (the opposite quads of the door...) but it is
                Spatial part = AssetUtils.loadModel(assetManager, modelName + "14");
                part.move(-TILE_WIDTH / 4, 0, -TILE_WIDTH / 4);
                moveSpatial(part, p);

                root.attachChild(part);

                door = true;
                continue;
            }

            Node model = constructQuad(assetManager, modelName, N, NE, E, SE, S, SW, W, NW,
                    northWestInside, northEastInside, southWestInside, southEastInside,
                    northInside, eastInside, southInside, westInside);
            moveSpatial(model, p);
            root.attachChild(model);
        }

        // Set the transform and scale to our scale and 0 the transform
        //AssetUtils.moveToTile(root, start);
        return root;
    }
}
