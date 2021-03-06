package com.gempukku.swccgo.logic.timing.results;

import com.gempukku.swccgo.game.PhysicalCard;
import com.gempukku.swccgo.game.SwccgGame;
import com.gempukku.swccgo.logic.GameUtils;
import com.gempukku.swccgo.logic.effects.MoveUsingLandspeedEffect;
import com.gempukku.swccgo.logic.effects.PreventableCardEffect;
import com.gempukku.swccgo.logic.timing.EffectResult;

import java.util.Collection;
import java.util.Collections;

/**
 * The effect result that is emitted when a card begins to move using landspeed.
 */
public class MovingUsingLandspeedResult extends EffectResult implements MovingResult {
    private PhysicalCard _cardMoving;
    private PhysicalCard _movingFrom;
    private PhysicalCard _movingTo;
    private boolean _asReact;
    private boolean _asMoveAway;
    private PreventableCardEffect _effect;

    /**
     * Creates an effect result that is emitted when a card begins to move using landspeed.
     * @param cardMoving the card that is moving
     * @param playerId the performing player
     * @param movingFrom the location the card is moving from
     * @param movingTo the location the card is moving to
     * @param asReact true if moved as 'react', otherwise false
     * @param asMoveAway true if 'move away', otherwise false
     * @param effect the effect that can be used to prevent the card from moving
     */
    public MovingUsingLandspeedResult(PhysicalCard cardMoving, String playerId, PhysicalCard movingFrom, PhysicalCard movingTo, boolean asReact, boolean asMoveAway, PreventableCardEffect effect) {
        super(Type.MOVING_USING_LANDSPEED, playerId);
        _cardMoving = cardMoving;
        _movingFrom = movingFrom;
        _movingTo = movingTo;
        _asReact = asReact;
        _asMoveAway = asMoveAway;
        _effect = effect;
    }

    /**
     * Gets the card that is moving.
     * @return the card that is moving
     */
    @Override
    public PhysicalCard getCardMoving() {
        return _cardMoving;
    }

    /**
     * Gets all the cards that are moving together.
     * @return the all the cards that are moving together
     */
    public Collection<PhysicalCard> getAllCardsMoving() {
        return Collections.unmodifiableCollection(((MoveUsingLandspeedEffect) _effect).getCardsMoving());
    }

   /**
     * Gets the location the card is moving from.
     * @return the location the card is moving from
     */
    @Override
    public PhysicalCard getMovingFrom() {
        return _movingFrom;
    }

    /**
     * Gets the location the card is moving to.
     * @return the location the card is moving to
     */
    @Override
    public PhysicalCard getMovingTo() {
        return _movingTo;
    }

    /**
     * Determine if movement is a 'react'.
     * @return true or false
     */
    @Override
    public boolean isReact() {
        return _asReact;
    }

    /**
     * Determine if movement is a 'move away'.
     * @return true or false
     */
    @Override
    public boolean isMoveAway() {
        return _asMoveAway;
    }

    /**
     * Gets the interface that can be used to prevent the card from moving, or null.
     * @return the interface
     */
    @Override
    public PreventableCardEffect getPreventableCardEffect() {
        return _effect;
    }

    /**
     * Gets the text to show to describe the effect result.
     * @param game the game
     * @return the text
     */
    @Override
    public String getText(SwccgGame game) {
        return "Moving " + (_asMoveAway ? "away " : "") + GameUtils.getCardLink(_cardMoving) + " from " + GameUtils.getCardLink(_movingFrom) + " to " + GameUtils.getCardLink(_movingTo) + " using landspeed" + (_asReact ? " as a 'react'" : "");
    }
}
