package com.FinalKill.wsbim.common.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.FinalKill.wsbim.WSBIM;
import com.FinalKill.wsbim.common.net.server.OpenGui;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketDispatcher
{
 // a simple counter will allow us to get rid of 'magic' numbers used during packet registration
 private static byte packetId = 0;
 
 /**
 * The SimpleNetworkWrapper instance is used both to register and send packets.
 * Since I will be adding wrapper methods, this field is private, but you should
 * make it public if you plan on using it directly.
 */
 private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(WSBIM.modid);

 /**
 * Call this during pre-init or loading and register all of your packets (messages) here
 */
 public static final void registerPackets() {
 // Using an incrementing field instead of hard-coded numerals, I don't need to think
 // about what number comes next or if I missed on should I ever rearrange the order
 // of registration (for instance, if you wanted to alphabetize them... yeah...)
 // It's even easier if you create a convenient 'registerMessage' method:
// PacketDispatcher.registerMessage(OpenGuiMessage.OpenGuiMessageHandler.class, OpenGuiMessage.class, Side.SERVER);
// PacketDispatcher.registerMessage(SyncPlayerPropsMessage.SyncPlayerPropsMessageHandler.class, SyncPlayerPropsMessage.class, Side.CLIENT);
 
 // If you don't want to make a 'registerMessage' method, you can do it directly:
PacketDispatcher.dispatcher.registerMessage(OpenGui.class, OpenGui.class, packetId++, Side.SERVER);
 //TODO PacketDispatcher.dispatcher.registerMessage(SyncPlayerPropsMessage.SyncPlayerPropsMessageHandler.class, SyncPlayerPropsMessage.class, packetId++, Side.CLIENT);
 }

 /**
 * Registers a message and message handler
 */
 private static final void registerMessage(Class handlerClass, Class messageClass, Side side) {
 PacketDispatcher.dispatcher.registerMessage(handlerClass, messageClass, packetId++, side);
 }


 //========================================================//
 // The following methods are the 'wrapper' methods; again,
 // this just makes sending a message slightly more compact
 // and is purely a matter of stylistic preference
 //========================================================//
 
 /**
 * Send this message to the specified player.
 * See {@link SimpleNetworkWrapper#sendTo(IMessage, EntityPlayerMP)}
 */
 public static final void sendTo(IMessage message, EntityPlayerMP player) {
 PacketDispatcher.dispatcher.sendTo(message, player);
 }

 /**
 * Send this message to everyone within a certain range of a point.
 * See {@link SimpleNetworkWrapper#sendToDimension(IMessage, NetworkRegistry.TargetPoint)}
 */
 public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
 PacketDispatcher.dispatcher.sendToAllAround(message, point);
 }

 /**
 * Sends a message to everyone within a certain range of the coordinates in the same dimension.
 */
 public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, 

double range) {
 PacketDispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, 

range));
 }

 /**
 * Sends a message to everyone within a certain range of the player provided.
 */
 public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
 PacketDispatcher.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, 

player.posY, player.posZ, range);
 }

 /**
 * Send this message to everyone within the supplied dimension.
 * See {@link SimpleNetworkWrapper#sendToDimension(IMessage, int)}
 */
 public static final void sendToDimension(IMessage message, int dimensionId) {
 PacketDispatcher.dispatcher.sendToDimension(message, dimensionId);
 }

 /**
 * Send this message to the server.
 * See {@link SimpleNetworkWrapper#sendToServer(IMessage)}
 */
 public static final void sendToServer(IMessage message) {
 PacketDispatcher.dispatcher.sendToServer(message);
 }
 public abstract class AbstractMessageHandler<T extends IMessage> implements IMessageHandler <T, IMessage>
 {
 /**
 * Handle a message received on the client side
 * @return a message to send back to the Server, or null if no reply is necessary
 */
 @SideOnly(Side.CLIENT)
 public abstract IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx);

 /**
 * Handle a message received on the server side
 * @return a message to send back to the Client, or null if no reply is necessary
 */
 public abstract IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx);

 /*
 * Here is where I parse the side and get the player to pass on to the abstract methods.
 * This way it is immediately clear which side received the packet without having to
 * remember or check on which side it was registered and the player is immediately
 * available without a lengthy syntax.
 */
 @Override
 public IMessage onMessage(T message, MessageContext ctx) {
 // due to compile-time issues, FML will crash if you try to use Minecraft.getMinecraft() here,
 // even when you restrict this code to the client side and before the code is ever accessed;
 // a solution is to use your proxy classes to get the player (see below).
 if (ctx.side.isClient()) {
 // the only reason to check side here is to use our more aptly named handling methods
 // client side proxy will return the client side EntityPlayer
 return handleClientMessage(WSBIM.proxy.getPlayerEntity(ctx), message, ctx);
 } else {
 // server side proxy will return the server side EntityPlayer
 return handleServerMessage(WSBIM.proxy.getPlayerEntity(ctx), message, ctx);
 }
 }
 }
 public abstract class AbstractClientMessageHandler<T extends IMessage> extends AbstractMessageHandler<T>


 {
 // implementing a final version of the server message handler both prevents it from
 // appearing automatically and prevents us from ever accidentally overriding it
 public final IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx) {
 return null;
 }
 }
 public abstract class AbstractServerMessageHandler<T extends IMessage> extends AbstractMessageHandler<T>


 {
 // implementing a final version of the client message handler both prevents it from
 // appearing automatically and prevents us from ever accidentally overriding it
 public final IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx) {
 return null;
 }
 }
}